package com.tumoji.tumoji.memes.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.memes.adapter.MemeUploadTagsRecyclerAdapter;
import com.tumoji.tumoji.memes.adapter.SelectTagsRecyclerAdapter;
import com.tumoji.tumoji.memes.contract.MemeUploadContract;
import com.tumoji.tumoji.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MemeUploadFragment extends Fragment implements MemeUploadContract.View, View.OnClickListener {
    private static final int REQUEST_PICK_MEME_IMAGE = 1;

    private OnFragmentInteractionListener mListener;
    private List<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> mTagsList = new ArrayList<>();
    private MemeUploadContract.Presenter mPresenter;
    private MemeUploadTagsRecyclerAdapter mTagsAdapter;
    private Uri mImageUri;

    private FrameLayout mMemeImageLayout;
    private ImageView mMemeImage;
    private FloatingActionButton mUploadFab;
    private EditText mTitleEdit;
    private RecyclerView mTagsRecyclerView;
    private ImageButton mEditTagsButton;
    private SelectTagsFragment mSelectTagsFragment;

    public static MemeUploadFragment newInstance() {
        return new MemeUploadFragment();
    }

    public MemeUploadFragment() {
        // Required empty public constructor
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    protected void uploadMeme(File file, String title, List<TagModel> tags) {
        mPresenter.requestUpload(file, title, tags);
    }

    private void setAllEnabled(boolean enabled) {
        mMemeImageLayout.setEnabled(enabled);
        mTitleEdit.setEnabled(enabled);
        mUploadFab.setEnabled(enabled);
        mEditTagsButton.setEnabled(enabled);
    }

    private void reloadTagsList(List<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> wrappers) {
        mTagsList.clear();
        mTagsList.addAll(wrappers);
        mTagsAdapter.reloadTags(getSelectedTags(wrappers));
    }

    private List<TagModel> getSelectedTags(List<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> wrappers) {
        ArrayList<TagModel> selected = new ArrayList<>();
        for (SelectTagsRecyclerAdapter.TagModelSelectableWrapper wrapper : wrappers) {
            if (wrapper.isSelected()) {
                selected.add(wrapper.getTagModel());
            }
        }
        return selected;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTagsAdapter = new MemeUploadTagsRecyclerAdapter();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meme_upload, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMemeImageLayout = (FrameLayout) getActivity().findViewById(R.id.meme_layout);
        mMemeImageLayout.setOnClickListener(this);
        mMemeImage = (ImageView) getActivity().findViewById(R.id.meme_image);
        mUploadFab = (FloatingActionButton) getActivity().findViewById(R.id.upload_fab);
        mUploadFab.setOnClickListener(this);
        mTitleEdit = (EditText) view.findViewById(R.id.title_edit);
        mTagsRecyclerView = (RecyclerView) view.findViewById(R.id.tags_recycler_view);
        mTagsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mTagsRecyclerView.setAdapter(mTagsAdapter);
        mEditTagsButton = (ImageButton) view.findViewById(R.id.edit_tags_button);
        mEditTagsButton.setOnClickListener(this);

        mPresenter.init();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_MEME_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                mImageUri = data.getData();
                Glide.with(getActivity()).load(mImageUri).into(mMemeImage);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mPresenter.requestStopUpload();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void refreshTagsList(List<TagModel> tagModels) {
        // Only cache them
        ArrayList<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> wrappers = new ArrayList<>();
        for (TagModel tagModel : tagModels) {
            SelectTagsRecyclerAdapter.TagModelSelectableWrapper wrapper = new SelectTagsRecyclerAdapter.TagModelSelectableWrapper();
            wrapper.setSelected(false);
            wrapper.setTagModel(tagModel);
            wrappers.add(wrapper);
        }
        reloadTagsList(wrappers);
    }

    @Override
    public void startUpload() {
        setAllEnabled(false);
    }

    @Override
    public void finishUploadAndClose() {
        Toast.makeText(getContext(), R.string.upload_successfully, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void failUploadWithNoMemeError() {
        setAllEnabled(true);
        View root = getView();
        if (root != null) {
            Snackbar.make(root, R.string.please_pick_a_meme, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failUploadWithNoTitleError() {
        setAllEnabled(true);
        View root = getView();
        if (root != null) {
            Snackbar.make(root, R.string.please_provide_the_meme_title, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void stopUploadAndClose() {
        getActivity().finish();
    }

    @Override
    public void setPresenter(MemeUploadContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showUnexpectedError(String message) {
        Toast.makeText(getContext(), getString(R.string.unexpected_error, message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.meme_layout:
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), REQUEST_PICK_MEME_IMAGE);
                break;
            case R.id.upload_fab:
                File memeFile = FileUtils.fromUri(getContext(), mImageUri);
                String memeTitle = mTitleEdit.getText().toString();
                List<TagModel> tagModels = getSelectedTags(mTagsList);
                MemeUploadFragmentPermissionsDispatcher.uploadMemeWithCheck(this, memeFile, memeTitle, tagModels);
                break;
            case R.id.edit_tags_button:
                mSelectTagsFragment = SelectTagsFragment.newInstance();
                mSelectTagsFragment.show(getActivity().getSupportFragmentManager(), SelectTagsFragment.class.toString());
                break;
            default:
                break;
        }
    }

    public void onSelectedTagsFragmentCreateView() {
        mSelectTagsFragment.reloadTagsList(mTagsList);
    }

    public void onFinishSelection(List<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> tagModels) {
        mSelectTagsFragment = null;
        reloadTagsList(tagModels);
    }

    public interface OnFragmentInteractionListener {
    }
}
