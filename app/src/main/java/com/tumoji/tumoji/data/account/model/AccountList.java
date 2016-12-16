package com.tumoji.tumoji.data.account.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by souler on 16-12-15.
 */
public class AccountList implements Serializable {
    private List<AccountModel> accounts;

    public List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountModel> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Accounts:" + accounts;
    }
}
