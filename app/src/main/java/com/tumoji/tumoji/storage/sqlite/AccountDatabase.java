package com.tumoji.tumoji.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tumoji.tumoji.data.account.model.AccountModel;

import java.util.ArrayList;

/**
 * Created by souler on 16-12-15.
 */
public class AccountDatabase {



    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase readableAccountDatas;
    private SQLiteDatabase writableAccountDatas;
    public static AccountDatabase accountDatabase;

    public static AccountDatabase getInstance(Context context) {
        if (accountDatabase == null) accountDatabase = new AccountDatabase(new DBOpenHelper(context));
        return accountDatabase;
    }

    private AccountDatabase(DBOpenHelper openHelper) {
        super();
        dbOpenHelper = openHelper;
        writableAccountDatas = openHelper.getWritableDatabase();
        readableAccountDatas = openHelper.getReadableDatabase();
    }

    /**
     * Get all local Accounts
     * @return a list of AccountModel
     */
    public ArrayList<AccountModel> getAllLocalAccounts() {
        ArrayList<AccountModel> ls = new ArrayList<>();
        if (readableAccountDatas == null) return null;

        /**
         * Perform query
         */
        Cursor accountCursor = readableAccountDatas.rawQuery("select * from " + DBOpenHelper.ACCOUNT_TABLE , null);
        while (accountCursor.moveToNext()) {
            String accountID = accountCursor.getString(accountCursor.getColumnIndex(DBOpenHelper.ACCOUNT_ID));
            String accountName = accountCursor.getString(accountCursor.getColumnIndex(DBOpenHelper.ACCOUNT_NAME));
            String accountEmail = accountCursor.getString(accountCursor.getColumnIndex(DBOpenHelper.ACCOUNT_EMAIL));
            String accountAvartar = accountCursor.getString(accountCursor.getColumnIndex(DBOpenHelper.ACCOUNT_AVATAR_URL));
            AccountModel tempModel = new AccountModel(accountID,accountAvartar,accountName,accountEmail);
            ls.add(tempModel);
        }
        accountCursor.close();
        return ls;

    }

    /**
     * Get account by name
     * @param name
     * @return accountmodel am
     */
    public AccountModel getAccountByName(String name) {
        if (readableAccountDatas == null) return null;
        AccountModel tempModel = null;

        /**
         * Perform query
         */
        Cursor cursor = readableAccountDatas.rawQuery("select * from " + DBOpenHelper.ACCOUNT_TABLE + " where " + DBOpenHelper.ACCOUNT_NAME + "=" + name , null);
        if (cursor.moveToNext()) {
            String accountID = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_ID));
            String accountName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_NAME));
            String accountEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_EMAIL));
            String accountAvartar = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_AVATAR_URL));
            tempModel = new AccountModel(accountID,accountAvartar,accountName,accountEmail);
            cursor.close();
        }
        return tempModel;
    }


    /**
     * meme Existence Checking function
     * @param id
     * @return boolean value of existency in name in database
     */
    private boolean checkAccountExistence(String id) {
        if (writableAccountDatas == null) return false;
        String queryStatement = " id=";
        Cursor cursor = writableAccountDatas.query(DBOpenHelper.ACCOUNT_TABLE , new String[] {
                DBOpenHelper.ACCOUNT_ID} , queryStatement , new String[] {
                id} , null , null , null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

    /**
     * Account adding function
     * @param accountModel
     * @return success or not
     */
    public boolean addAccount(AccountModel accountModel) {
        if (writableAccountDatas == null || accountModel == null || checkAccountExistence(accountModel.getUserId())) return false;

        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.ACCOUNT_ID , accountModel.getUserId());
        cv.put(DBOpenHelper.ACCOUNT_NAME , accountModel.getUsername());
        cv.put(DBOpenHelper.ACCOUNT_EMAIL , accountModel.getEmail());
        cv.put(DBOpenHelper.ACCOUNT_AVATAR_URL , accountModel.getAvatarUrl());

        writableAccountDatas.insert(DBOpenHelper.ACCOUNT_TABLE , null , cv);
        return true;
    }


    /**
     * Account removal function
     * @param accountModel
     * @return success or not
     */
    public boolean removeAccount(AccountModel accountModel) {
        if (writableAccountDatas == null || accountModel == null || !checkAccountExistence(accountModel.getUserId())) return false;

        writableAccountDatas.delete(DBOpenHelper.ACCOUNT_TABLE , DBOpenHelper.ACCOUNT_ID + "=?" , new String []{accountModel.getUserId()});

        return true;

    }


    /**
     * Account updation Function
     * @param accountModel
     * @return success or not
     */
    public boolean updateAccount(AccountModel accountModel) {
        if (writableAccountDatas == null || accountModel == null || !checkAccountExistence(accountModel.getUserId())) return false;

        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.ACCOUNT_ID , accountModel.getUserId());
        cv.put(DBOpenHelper.ACCOUNT_NAME , accountModel.getUsername());
        cv.put(DBOpenHelper.ACCOUNT_EMAIL , accountModel.getEmail());
        cv.put(DBOpenHelper.ACCOUNT_AVATAR_URL , accountModel.getAvatarUrl());


        writableAccountDatas.update(DBOpenHelper.ACCOUNT_TABLE , cv , DBOpenHelper.ACCOUNT_ID+"="+accountModel.getUserId() , null);
        return true;
    }

    /**
     * Account Synchronizical function to sync datas in repo and database
     * @param toBeSinced
     * @return
     */
    public ArrayList<AccountModel> syncAccount(ArrayList<AccountModel> toBeSinced) {
        if (toBeSinced == null || readableAccountDatas == null) return getAllLocalAccounts();
        for (int i = 0 ; i < toBeSinced.size() ; i++) {
            addAccount(toBeSinced.get(i));
        }
        return getAllLocalAccounts();
    }

}
