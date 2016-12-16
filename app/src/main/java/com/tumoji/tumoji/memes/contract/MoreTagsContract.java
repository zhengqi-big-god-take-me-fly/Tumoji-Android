package com.tumoji.tumoji.memes.contract;

import com.tumoji.tumoji.common.BaseView;
import com.tumoji.tumoji.data.tag.model.TagModel;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/16/16
 */

public interface MoreTagsContract {
    interface Presenter {
        void init();
    }

    interface View extends BaseView<Presenter> {
        void startLoading();

        void finishLoading(List<TagModel> tagModels);
    }
}
