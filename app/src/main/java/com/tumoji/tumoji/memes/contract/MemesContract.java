package com.tumoji.tumoji.memes.contract;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.account.model.AccountModel;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;

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
         * Called when user click some meme in the memes list.
         * @param memeModel Meme data object clicked by user
         */
        void memeThumbnailItemClicked(MemeModel memeModel);

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
         * Update tags list.
         */
        void updateTagsList();

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
         * Popup a sheet to display HD meme image.
         * @param memeModel The meme data object to be displayed
         */
        void showHdMeme(MemeModel memeModel);

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
         * Refresh HD meme info with new model
         * @param newMemeModel The new meme data object to be displayed
         */
        void refreshHdMeme(MemeModel newMemeModel);

        /**
         * Refresh user's information shown in navigation drawer.
         * @param accountModel The model where user's information stored.
         */
        void refreshUserInfo(AccountModel accountModel);

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
