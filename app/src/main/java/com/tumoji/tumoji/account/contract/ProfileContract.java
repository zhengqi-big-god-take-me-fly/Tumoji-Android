package com.tumoji.tumoji.account.contract;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.data.meme.model.MemeModel;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/15/16
 */

public interface ProfileContract {
    interface Presenter {
        void init();

        void changeAvatar(String newImage);

        void requestUploadMeme();

        void signOut();
    }

    interface View extends BaseView<Presenter> {
        void refreshProfile(AccountModel accountModel);

        void refreshUploadedMemes(List<MemeModel> memeModels);

        void closeProfilePage();
    }
}
