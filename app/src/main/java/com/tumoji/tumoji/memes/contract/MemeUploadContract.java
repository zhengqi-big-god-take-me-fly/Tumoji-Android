package com.tumoji.tumoji.memes.contract;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/17/16
 */

public interface MemeUploadContract {
    interface Presenter {
        void requestUpload(Uri memePath, @NonNull String memeTitle, List<TagModel> tagModels);

        void requestStopUpload();
    }

    interface View extends BaseView<Presenter> {
        void startUpload();

        void finishUploadAndClose();

        void failUploadWithNoMemeError();

        void failUploadWithNoTitleError();

        void stopUploadAndClose();
    }
}
