package com.tumoji.tumoji.memes.contract;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.user.model.UserModel;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/16/16
 */

public interface MemeDetailContract {
    interface Presenter {
        /**
         * Setup initial data presenting. Called when the view is created.
         */
        void init(String memeId);

        /**
         * Called when user likes some meme.
         * @param memeModel The meme data object user likes.
         */
        void likeMeme(MemeModel memeModel);

        /**
         * Called when user unlikes some meme.
         * @param memeModel The meme data object user likes.
         */
        void unlikeMeme(MemeModel memeModel);

        /**
         * Called when user reports some meme.
         * @param memeModel The meme data object user reports.
         * @param reason The report reason.
         */
        void reportMeme(MemeModel memeModel, String reason);

        /**
         * Save meme file to local
         * @param mMemeModel Meme data object
         */
        void saveMeme(MemeModel mMemeModel);
    }

    interface View extends BaseView<Presenter> {
        void refreshMemeDetail(MemeModel memeModel);

        void refreshMemeName(String name);

//        void refreshMemeUploader();

        void refreshMemeTags(List<TagModel> tagModels);

        void refreshMemeLikeStatus(boolean like, int count);

        void refreshMemeReportStatus(boolean report, int count);

        void refreshMemeAuthor(UserModel userModel);

        void showUnSignedInError();
    }
}
