package com.tumoji.tumoji.data.account.repository;

import com.tumoji.tumoji.common.OnGetNaiveResultListener;
import com.tumoji.tumoji.common.OnGetResultListener;
import com.tumoji.tumoji.data.account.model.AccountModel;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public interface IAccountRepository {
    /**
     * Get the account of current signed in user
     * @return The model of current signed in user, or null if no signed in user.
     */
    AccountModel getSignedInAccount();

    /**
     * Check whether there is a signed in account. Use this instead of {@link #getSignedInAccount()}
     * for quick check
     * @return Whether there is a signed in account
     */
    boolean hasSignedInAccount();

    /**
     * Get latest account profile and update local profile data
     * @param listener Callback
     */
    void updateSignedInAccount(OnGetResultListener<AccountModel> listener);

    /**
     * Sign out currently signed in user and give operation result
     * @param listener Callback
     */
    void signOut(OnGetNaiveResultListener listener);
}
