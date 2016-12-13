package com.tumoji.tumoji.memes.contract;

import com.tumoji.tumoji.common.BaseView;
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
         * Called when user reports some meme.
         * @param memeModel The meme data object user reports.
         */
        void reportMeme(MemeModel memeModel);

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
        void refershPopularMemesList(List<MemeModel> memeModels, int offset);

        /**
         * Refresh new memes list
         * @param memeModels New memes list
         * @param offset Populate new memes with offset in the list
         */
        void refreshNewMemesList(List<MemeModel> memeModels, int offset);

        /**
         * Refresh top tags list.
         * @param tagModels Top tags list
         */
        void refreshTopTagsList(List<TagModel> tagModels);

        /**
         * Set selected tag.
         * @param tagModel The tag to be selected.
         */
        void setSelectedTag(TagModel tagModel);
    }
}
