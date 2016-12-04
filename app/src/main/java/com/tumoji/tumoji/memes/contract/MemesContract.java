package com.tumoji.tumoji.memes.contract;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.meme.bean.Meme;
import com.tumoji.tumoji.data.tag.bean.Tag;

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
         * @param meme Meme data object clicked by user
         */
        void memeThumbnailItemClicked(Meme meme);

        /**
         * Called when user likes some meme.
         * @param meme The meme data object user likes.
         */
        void likeMeme(Meme meme);

        /**
         * Called when user reports some meme.
         * @param meme The meme data object user reports.
         */
        void reportMeme(Meme meme);

        /**
         * Update popular memes list
         * @param offset Update memes from the offset
         */
        void updatePopularMemesList(int offset);

        /**
         * Update popular memes list of specific tag.
         * @param offset Update memes from the offset
         * @param tag The tag user selects
         */
        void updatePopularMemesListOfTag(int offset, Tag tag);

        /**
         * Update new memes list
         * @param offset Update memes from the offset
         */
        void updateNewMemesList(int offset);

        /**
         * Update new memes list of specific tag.
         * @param offset Update memes from the offset
         * @param tag The tag user selects.
         */
        void updateNewMemesListOfTag(int offset, Tag tag);

        /**
         * Called when user click on the Upload Meme button
         */
        void requestUploadingMeme();
    }

    interface View extends BaseView<Presenter> {
        /**
         * Popup a sheet to display HD meme image.
         * @param meme The meme data object to be displayed
         */
        void showHdMeme(Meme meme);

        /**
         * Refresh popular memes list
         * @param memes Popular memes list
         * @param offset Populate new memes with offset in the list
         */
        void refershPopularMemesList(List<Meme> memes, int offset);

        /**
         * Refresh new memes list
         * @param memes New memes list
         * @param offset Populate new memes with offset in the list
         */
        void refreshNewMemesList(List<Meme> memes, int offset);

        /**
         * Refresh top tags list.
         * @param tags Top tags list
         */
        void refreshTopTagsList(List<Tag> tags);

        /**
         * Set selected tag.
         * @param tag The tag to be selected.
         */
        void setSelectedTag(Tag tag);
    }
}
