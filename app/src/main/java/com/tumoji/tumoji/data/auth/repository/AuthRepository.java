package com.tumoji.tumoji.data.auth.repository;

import android.content.Context;

import com.tumoji.tumoji.data.auth.model.AuthModel;
import com.tumoji.tumoji.data.auth.store.LocalAuthStore;
import com.tumoji.tumoji.data.auth.store.RemoteAuthStore;

import rx.Observable;

/**
 * Author: souler
 * Date  : 16-12-19
 *
 * NOTE: This repository only take charge of account authentication.
 */
public class AuthRepository implements IAuthRepository {
    private LocalAuthStore mLocal;
    private RemoteAuthStore mRemote;

//
//    private SharedPreferences mSharedPreferences;
//    // Current User
//    private AuthModel currentUser;
//
//     Passin Argument used for login;
//    private AccountLoginModel argumentUser;
//     Current Token
//     NOTE: May be replaced with single token string or saved fully to preference, not sure
//    private Token mToken;
//
//     All users on the server
//    private ArrayList<AccountModel> allUserList;

    // Singleton
    private static AuthRepository sInstance;


//    public AccountLoginModel getArgumentUser() {
//        return argumentUser;
//    }
//
//    public void setArgumentUser(AccountLoginModel argumentUser) {
//        this.argumentUser = argumentUser;
//    }

    private AuthRepository(Context context) {
//        mSharedPreferences = SharedPreferencesFactory.getAppDefaultSharedPreferences(context);
//         TODO: Read current user from local storage
//        currentUser = null;
//        mToken = new Token();
//        mToken.setId(mSharedPreferences.getString(SharedPreferencesFactory.PK_ACCESS_TOKEN, null));
//        argumentUser = null;
//        allUserList = new ArrayList<>();
        mLocal = new LocalAuthStore(context);
        mRemote = new RemoteAuthStore();
    }

    public static AuthRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AuthRepository(context);
        }
        return sInstance;
    }

    /**
     * Get the account of current signed in user
     *
     * @return The model of current signed in user, or null if no signed in user.
     */
    @Override
    public AuthModel getLocalAuth() {
        return mLocal.getLocalAuth();
    }

//    /**
//     * Check whether there is a signed in account. Use this instead of {@link #getLocalAuth()}
//     * for quick check
//     *
//     * @return Whether there is a signed in account
//     */
//    @Override
//    public boolean hasSignedInAccount() {
//        return mLocal.available();
//    }

    @Override
    public boolean isSignedIn() {
        return mLocal.available();
    }

//    /**
//     * TODO: Should be able to get user profile with token only which is the responsibility of server
//     * Get latest account profile and update local profile data
//     * It's impossible get the account profile unless the user passin arguments
//     * Since the server api needs username and password for login.
//     * @param listener Callback
//     */
//    @Override
//    public void updateSignedInAccount(OnGetResultListener<AuthModel> listener) {
//        if (argumentUser == null) listener.onFailure(404 , "Please set the username and password for login user");
//        // If no login
//        if (currentUser == null) {
//            //make json body
//            Gson gson = new Gson();
//            String route = gson.toJson(argumentUser);
//            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
//            // Get login token
//            mAccountApi.requestLogin(body)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(token -> {
//                        this.mToken = token;
//                    });
//            // Get user information
//            mAccountApi.getUserById(mToken.getUserId())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(accountModel -> {
//                        this.currentUser = new AuthModel();
//                        this.currentUser.setUserId(this.mToken.getUserId());
//                        this.currentUser.setEmail(accountModel.getEmail());
//                        this.currentUser.setUsername(accountModel.getUsername());
//                        this.currentUser.setAvatarUrl(accountModel.getAvatarUrl());
//                    });
//            // Here we successfully updated the infos in the repo.Now we add it into the database
//
//        }
//        listener.onSuccess(currentUser);
//    }
//
//    @Override
//    public Observable<AuthModel> updateSignedInAccount() {
//        // NOTE: Ehh...why no /users/me API?
//        return mAccountApi.getUserById(mToken.getUserId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<AuthModel, Observable<AuthModel>>() {
//                    @Override
//                    public Observable<AuthModel> call(AuthModel authModel) {
//                        return Observable.create(new Observable.OnSubscribe<AuthModel>() {
//                            @Override
//                            public void call(Subscriber<? super AuthModel> subscriber) {
//                                try {
//                                    if (!subscriber.isUnsubscribed()) {
//                                        // TODO: Save account profile to local storage
//                                        currentUser = authModel;
//                                        subscriber.onNext(authModel);
//                                        subscriber.onCompleted();
//                                    }
//                                } catch (Exception e) {
//                                    subscriber.onError(e);
//                                }
//                            }
//                        });
//                    }
//                });
//    }
//
//    /**
//     * Sign out currently signed in user and give operation result
//     *
//     * @param listener Callback
//     */
//    @Override
//    public void signOut(OnGetNaiveResultListener listener) {
//        if (mToken == null) listener.onFailure(404 , "User not login , cannot logout");
//
//        /**
//         * Request logout
//         */
//        mAccountApi.requstLogout(mToken.getId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(errorType -> {
//                    mToken = null;
//                    currentUser = null;
//                });
//        // History problem
//        mToken = null;
//        currentUser = null;
//        listener.onSuccess();
//    }

    @Override
    public Observable<Void> signOut() {
        return Observable.concat(mRemote.removeAuth(mLocal.getAccessToken()), mLocal.removeAuth());
    }

//    @Override
//    public void signUp(String username, String email, String password, OnGetNaiveResultListener listener) {
//        // TODO
////        throw new UnsupportedOperationException("Method not implemented");
//    }
//
//    @Override
//    public void signIn(String usernameOrEmail, boolean isUsername, String password, OnGetNaiveResultListener listener) {
//        // TODO
////        throw new UnsupportedOperationException("Method not implemented");
//    }

    @Override
    public Observable<AuthModel> signIn(String usernameOrEmail, boolean isUsername, String password) {
//        Gson gson = new Gson();
//        if (argumentUser == null) {
//            argumentUser = new AccountLoginModel();
//        }
//        if (isUsername) {
//            argumentUser.setUsername(usernameOrEmail);
//        } else {
//            argumentUser.setEmail(usernameOrEmail);
//        }
//        argumentUser.setPassword(password);
//        String route = gson.toJson(argumentUser);
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);
//        return mAccountApi.requestLogin(body)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<Token, Observable<Void>>() {
//                    @Override
//                    public Observable<Void> call(Token token) {
//                        return Observable.create(new Observable.OnSubscribe<Void>() {
//                            @Override
//                            public void call(Subscriber<? super Void> subscriber) {
//                                try {
//                                    mToken = token;
//                                    mSharedPreferences.edit().putString(SharedPreferencesFactory.PK_ACCESS_TOKEN, token.getId()).apply();
//                                    subscriber.onNext(null);
//                                    subscriber.onCompleted();
//                                } catch (Exception e) {
//                                    subscriber.onError(e);
//                                }
//                            }
//                        });
//                    }
//                }).flatMap(new Func1<Void, Observable<AuthModel>>() {
//                    @Override
//                    public Observable<AuthModel> call(Void aVoid) {
//                        return updateSignedInAccount();
//                    }
//                });
        return mRemote.makeAuth(usernameOrEmail, isUsername, password)
                .flatMap(authModel -> mLocal.saveAuth(authModel));
    }

//    @Override
//    public Observable<FindAccountResultModel> findAccount(String usernameOrEmail) {
//        return mAccountApi.findAccount(usernameOrEmail, usernameOrEmail)
//                .compose(NetworkScheduler.applySchedulers())
//                .map(accountModels -> {
//                    if (accountModels.size() == 0) {
//                        return new FindAccountResultModel(FindAccountResultModel.RESULT_NOT_FOUND);
//                    } else if (accountModels.get(0).getUsername().equals(usernameOrEmail)) {
//                        return new FindAccountResultModel(FindAccountResultModel.RESULT_USERNAME_FOUND);
//                    } else {
//                        return new FindAccountResultModel(FindAccountResultModel.RESULT_EMAIL_FOUND);
//                    }
//                });
//    }

//    @Override
//    public Observable<AuthModel> signUp(String username, String email, String password) {
//        mRemote.signUpAndMakeAuth(username, email, password);
//    }
}
