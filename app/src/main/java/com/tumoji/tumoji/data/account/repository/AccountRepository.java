package com.tumoji.tumoji.data.account.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.google.gson.Gson;
import com.tumoji.tumoji.common.OnGetNaiveResultListener;
import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.account.model.AccountLoginModel;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.AccountAPI;
import com.tumoji.tumoji.storage.preferences.SharedPreferencesFactory;
import com.tumoji.tumoji.utils.Token;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author: souler
 * Date  : 16-12-19
 */
public class AccountRepository implements IAccountRepository {
    private SharedPreferences mSharedPreferences;
    // Passin Argument used for login;
    private AccountLoginModel argumentUser;
    // Current User
    private AccountModel currentUser;
    // Current Token
    // NOTE: May be replaced with single token string or saved fully to preference, not sure
    private Token mToken;

    // All users on the server
    private ArrayList<AccountModel> allUserList;
    // Singleton
    private static AccountRepository accountRepository;

    private Handler mHandler;

    // Well , I do think that Retrofit should used in presenter level
    private static final AccountAPI accountApi = APIFactory.getAccountAPIInstance();


    public AccountLoginModel getArgumentUser() {
        return argumentUser;
    }

    public void setArgumentUser(AccountLoginModel argumentUser) {
        this.argumentUser = argumentUser;
    }

    private AccountRepository(Context context) {
        mSharedPreferences = SharedPreferencesFactory.getAppDefaultSharedPreferences(context);
        // TODO: Read current user from local storage
        currentUser = null;
        mToken = new Token();
        mToken.setId(mSharedPreferences.getString(SharedPreferencesFactory.PK_ACCOUNT_TOKEN, null));
        argumentUser = null;
        allUserList = new ArrayList<>();
        mHandler = new Handler();
    }


    public static AccountRepository getInstance(Context context) {
        if (accountRepository == null) {
            accountRepository = new AccountRepository(context);
        }
        return accountRepository;
    }

    /**
     * Get the account of current signed in user
     *
     * @return The model of current signed in user, or null if no signed in user.
     */
    @Override
    public AccountModel getSignedInAccount() {
        return currentUser;
    }

    /**
     * Check whether there is a signed in account. Use this instead of {@link #getSignedInAccount()}
     * for quick check
     *
     * @return Whether there is a signed in account
     */
    @Override
    public boolean hasSignedInAccount() {
        return currentUser != null;
    }

    /**
     * TODO: Should be able to get user profile with token only which is the responsibility of server
     * Get latest account profile and update local profile data
     * It's impossible get the account profile unless the user passin arguments
     * Since the server api needs username and password for login.
     * @param listener Callback
     */
    @Override
    public void updateSignedInAccount(OnGetResultListener<AccountModel> listener) {
        if (argumentUser == null) listener.onFailure(404 , "Please set the username and password for login user");
        // If no login
        if (currentUser == null) {
            //make json body
            Gson gson = new Gson();
            String route = gson.toJson(argumentUser);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
            // Get login token
            accountApi.requestLogin(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(token -> {
                        this.mToken = token;
                    });
            // Get user Infomation
            accountApi.getUserById(mToken.getUserId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(accountModel -> {
                        this.currentUser = new AccountModel();
                        this.currentUser.setUserId(this.mToken.getUserId());
                        this.currentUser.setEmail(accountModel.getEmail());
                        this.currentUser.setUsername(accountModel.getUsername());
                        this.currentUser.setAvatarUrl(accountModel.getAvatarUrl());
                    });
            // Here we successfully updated the infos in the repo.Now we add it into the database

        }
        listener.onSuccess(currentUser);
    }

    /**
     * Sign out currently signed in user and give operation result
     *
     * @param listener Callback
     */
    @Override
    public void signOut(OnGetNaiveResultListener listener) {
        if (mToken == null) listener.onFailure(404 , "User not login , cannot logout");

        /**
         * Request logout
         */
        accountApi.requstLogout(mToken.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorType -> {
                    mToken = null;
                    currentUser = null;
                });
        // History problem
        mToken = null;
        currentUser = null;
        listener.onSuccess();
    }

    @Override
    public Observable<Void> signOut() {
        return accountApi.requstLogout(mToken.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(errorType -> Observable.create((Observable.OnSubscribe<Void>) subscriber -> {
                    try {
                        if (errorType.getError().getStatus() != 204) {
                            throw new Exception(errorType.getError().getMessage());
                        }
                        mToken = null;
                        currentUser = null;
                        mSharedPreferences.edit().remove(SharedPreferencesFactory.PK_ACCOUNT_TOKEN).apply();
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }));
    }

    @Override
    public void signUp(String username, String email, String password, OnGetNaiveResultListener listener) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void signIn(String usernameOrEmail, boolean isUsername, String password, OnGetNaiveResultListener listener) {
        // TODO
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public Observable<Void> signIn(String usernameOrEmail, boolean isUsername, String password) {
        Gson gson = new Gson();
        if (argumentUser == null) {
            argumentUser = new AccountLoginModel();
        }
        if (isUsername) {
            argumentUser.setUsername(usernameOrEmail);
        } else {
            argumentUser.setEmail(usernameOrEmail);
        }
        argumentUser.setPassword(password);
        String route = gson.toJson(argumentUser);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);
        return accountApi.requestLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Token, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(Token token) {
                        return Observable.create(new Observable.OnSubscribe<Void>() {
                            @Override
                            public void call(Subscriber<? super Void> subscriber) {
                                try {
                                    mToken = token;
                                    mSharedPreferences.edit().putString(SharedPreferencesFactory.PK_ACCOUNT_TOKEN, token.getId()).apply();
                                    subscriber.onNext(null);
                                    subscriber.onCompleted();
                                } catch (Exception e) {
                                    subscriber.onError(e);
                                }
                            }
                        });
                    }
                });
    }
}
