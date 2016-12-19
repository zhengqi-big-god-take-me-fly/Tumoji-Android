package com.tumoji.tumoji.data.tag.repository;

import android.nfc.Tag;

import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.TagAPI;
import com.tumoji.tumoji.storage.sqlite.TagDatabase;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by souler on 16-12-14.
 */
public class TagRepository implements ITagRepository {
    // Singleton
    private static TagRepository tagRepository;
    // List of all tags
    private ArrayList<TagModel> tagModels;
    // TagApi
    private static final TagAPI tagApi = APIFactory.getTagAPIInstance();
    // Tag Database Controller
    private static final TagDatabase tagDatabase = TagDatabase.getInstance();
    // Get tag Counts
    private int tagCount;

    public static TagRepository getInstance() {
        if (tagRepository == null) {
            tagRepository = new TagRepository();
        }
        return tagRepository;
    }

    private TagRepository() {
        tagModels = null;
        tagCount = 0;
    }



    /**
     * Get latest tags list from API server.
     * NOTE: You should cache the gotten tags list to local storage (SQLite, etc.) for the further
     * calls of {@link #getCachedTagsList() getCachedTagsList} method.
     *
     * @param listener Result callback
     */
    @Override
    public void getTagsList(OnGetTagsListListener listener) {

        /**
         * Fetch list from server
         */
        tagApi.getAllTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tagModels1 -> {
                    for (TagModel tagModel : tagModels1) {
                        this.tagModels.add(tagModel);
                    }
                });
        /**
         * Update to the database
         */
        tagDatabase.syncTag(this.tagModels);

        listener.onSuccess(this.tagModels);

    }

    /**
     * Get cached tags list from local storage
     *
     * @return Cached tags list
     */
    @Override
    public List<TagModel> getCachedTagsList() {
        return tagDatabase.getAllLocalTags();
    }
}
