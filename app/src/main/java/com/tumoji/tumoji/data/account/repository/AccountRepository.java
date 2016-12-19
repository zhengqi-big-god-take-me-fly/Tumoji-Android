package com.tumoji.tumoji.data.account.repository;

import android.os.Handler;

import com.google.gson.Gson;
import com.tumoji.tumoji.common.OnGetNaiveResultListener;
import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.account.model.AccountLoginModel;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.AccountAPI;
import com.tumoji.tumoji.utils.Token;

import java.util.ArrayList;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by souler on 16-12-19.
 */
public class AccountRepository implements IAccountRepository {
    // Passin Argument used for login;
    private AccountLoginModel argumentUser;
    // Current User
    private AccountModel currentUser;
    // Current Token
    private Token token;

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

    private AccountRepository() {
        currentUser = null;
        token = null;
        argumentUser = null;
        allUserList = new ArrayList<>();
        mHandler = new Handler();
    }


    public static AccountRepository getInstance() {
        if (accountRepository == null) {
            accountRepository = new AccountRepository();
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
        return currentUser == null ? false : true;
    }

    /**
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
                        this.token = token;
                    });
            // Get user Infomation
            accountApi.getUserById(token.getUserId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(accountModel -> {
                        this.currentUser = new AccountModel();
                        this.currentUser.setUserId(this.token.getUserId());
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
        if (token == null) listener.onFailure(404 , "User not login , cannot logout");

        /**
         * Request logout
         */
        accountApi.requstLogout(token.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorType -> {
                    token = null;
                    currentUser = null;
                });
        // History problem
        token = null;
        currentUser = null;
        listener.onSuccess();
    }
}
