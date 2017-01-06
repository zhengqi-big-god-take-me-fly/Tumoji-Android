package com.tumoji.tumoji.data.tag.repository;

import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.network.retrofit.APIFactory;
import com.tumoji.tumoji.network.retrofit.TagAPI;
import com.tumoji.tumoji.storage.sqlite.TagDatabase;
import com.tumoji.tumoji.utils.ApplySchedulers;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

    @Override
    public Observable<Void> addTagsForMeme(String token, String memeId, List<TagModel> tagModels) {
        ArrayList<Observable<TagModel>> observables = new ArrayList<>();
        // FIXME: Check tag existence and choose to add new tag or rel a tag
        for (TagModel tagModel : tagModels) {
            observables.add(tagApi.relTagToMeme(memeId, tagModel.getTagName(), token).compose(ApplySchedulers.network()));
        }
        return Observable.merge(observables).toList().map(new Func1<List<TagModel>, Void>() {
            @Override
            public Void call(List<TagModel> tagModels) {
                return null;
            }
        });
    }

    @Override
    public Observable<List<TagModel>> getTagsList() {
        return tagApi.getAllTags().compose(ApplySchedulers.network());
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

    @Override
    public Observable<List<TagModel>> getTagsListOfMeme(String memeId) {
        return tagApi.getTagsOfMeme(memeId).compose(ApplySchedulers.network());
    }
}
