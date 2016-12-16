package com.tumoji.tumoji.memes.presenter;

import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.ITagRepository;
import com.tumoji.tumoji.memes.contract.MoreTagsContract;

import java.util.List;

/**
 * Author: perqin
 * Date  : 12/16/16
 */

public class MoreTagsPresenter implements MoreTagsContract.Presenter {
    private ITagRepository mTagRepository;
    private MoreTagsContract.View mView;

    public MoreTagsPresenter(ITagRepository tagRepository, MoreTagsContract.View view) {
        mTagRepository = tagRepository;
        mView = view;
    }

    @Override
    public void init() {
        mView.startLoading();
        mTagRepository.getTagsList(new ITagRepository.OnGetTagsListListener() {
            @Override
            public void onSuccess(List<TagModel> tagModels) {
                mView.finishLoading(tagModels);
            }

            @Override
            public void onFailure(int error, String msg) {
                throw new UnsupportedOperationException("Method not implemented");
            }
        });
    }
}
