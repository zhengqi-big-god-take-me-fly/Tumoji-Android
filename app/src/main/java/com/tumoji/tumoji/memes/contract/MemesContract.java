package com.tumoji.tumoji.memes.contract;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.user.model.UserModel;

import java.util.List;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

public interface MemesContract {
    interface Presenter {
        /**
         * Setup initial data presenting. Called when the view is created.
         */
        void init();

        /**
         * Sync or update something here. Called when the view is resumed.
         */
        void viewResume();

        /**
         * Update popular memes list
         * @param offset Update memes from the offset
         */
        void updatePopularMemesList(int offset);

        /**
         * Update popular memes list of specific tag.
         * @param offset Update memes from the offset
         * @param tagModel The tag user selects
         */
        void updatePopularMemesListOfTag(int offset, TagModel tagModel);

        /**
         * Update new memes list
         * @param offset Update memes from the offset
         */
        void updateNewMemesList(int offset);

        /**
         * Update new memes list of specific tag.
         * @param offset Update memes from the offset
         * @param tagModel The tag user selects.
         */
        void updateNewMemesListOfTag(int offset, TagModel tagModel);

        /**
         * Called when user click on the Upload Meme button
         */
        void requestUploadingMeme();

        /**
         * Called when user click the avatar to see profile or sign in/sign up.
         */
        void requestOpenUserProfilePage();

        /**
         * Called when user select or deselect a tag
         * @param tagModel The tag user select, or null if user deselect the tag
         */
        void changeTag(TagModel tagModel);
    }

    interface View extends BaseView<Presenter> {
        /**
         * Refresh popular memes list
         * @param memeModels Popular memes list
         * @param offset Populate new memes with offset in the list
         */
        void refreshPopularMemesList(List<MemeModel> memeModels, int offset);

        /**
         * Refresh new memes list
         * @param memeModels New memes list
         * @param offset Populate new memes with offset in the list
         */
        void refreshNewMemesList(List<MemeModel> memeModels, int offset);

        /**
         * Refresh tags list.
         * @param tagModels Tags list
         */
        void refreshTagsList(List<TagModel> tagModels);

        /**
         * Refresh user's information shown in navigation drawer.
         * @param userModel The model where user's information stored.
         */
        void refreshUserInfo(UserModel userModel);

        /**
         * Go to user profile page
         */
        void gotoProfilePage();

        /**
         * Go to sign in/sign up page
         */
        void gotoSignInSignUpPage();

        /**
         * Go to meme upload page
         */
        void gotoMemeUploadPage();
    }
}
