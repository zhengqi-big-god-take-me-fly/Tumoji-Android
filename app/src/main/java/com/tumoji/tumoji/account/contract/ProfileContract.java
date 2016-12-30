package com.tumoji.tumoji.account.contract;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.user.model.UserModel;

/**
 * Author: perqin
 * Date  : 12/15/16
 */

public interface ProfileContract {
    interface Presenter {
        void init();

        void changeAvatar(String newImage);

//        void requestUploadMeme();

        void signOut();
    }

    interface View extends BaseView<Presenter> {
        void refreshProfile(UserModel userModel);

//        void refreshUploadedMemes(List<MemeModel> memeModels);

        void closeProfilePage();
    }
}
