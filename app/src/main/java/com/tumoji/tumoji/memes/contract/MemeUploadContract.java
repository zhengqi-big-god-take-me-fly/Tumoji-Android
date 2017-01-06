package com.tumoji.tumoji.memes.contract;

import android.support.annotation.NonNull;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.io.File;
import java.util.List;

/**
 * Author: perqin
 * Date  : 12/17/16
 */

public interface MemeUploadContract {
    interface Presenter {
        void init();

        void requestUpload(File memeFile, @NonNull String memeTitle, List<TagModel> tagModels);

        void requestStopUpload();
    }

    interface View extends BaseView<Presenter> {
        void refreshTagsList(List<TagModel> tagModels);

        void startUpload();

        void finishUploadAndClose();

        void failUploadWithNoMemeError();

        void failUploadWithNoTitleError();

        void stopUploadAndClose();
    }
}
