package com.tumoji.tumoji.data.auth.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by souler on 16-12-15.
 */
public class AccountList implements Serializable {
    private List<AuthModel> accounts;

    public List<AuthModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AuthModel> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Accounts:" + accounts;
    }
}
