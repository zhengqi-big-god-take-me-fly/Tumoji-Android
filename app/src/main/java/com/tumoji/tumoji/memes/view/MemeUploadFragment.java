package com.tumoji.tumoji.memes.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.memes.contract.MemeUploadContract;

public class MemeUploadFragment extends Fragment implements MemeUploadContract.View, View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    private FrameLayout mMemeImageLayout;
    private ImageView mMemeImage;
    private FloatingActionButton mUploadFab;
    private EditText mTitleEdit;
    private RecyclerView mTagsRecyclerView;
    private ImageButton mEditTagsButton;
    private MemeUploadContract.Presenter mPresenter;

    public static MemeUploadFragment newInstance() {
        return new MemeUploadFragment();
    }

    public MemeUploadFragment() {
        // Required empty public constructor
    }

    private void setAllEnabled(boolean enabled) {
        mMemeImageLayout.setEnabled(enabled);
        mTitleEdit.setEnabled(enabled);
        mUploadFab.setEnabled(enabled);
        mEditTagsButton.setEnabled(enabled);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        mEditTagsButton = (ImageButton) view.findViewById(R.id.edit_tags_button);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.meme_layout:
                // TODO: Pick image
                break;
            case R.id.upload_fab:
                mPresenter.requestUpload(null, mTitleEdit.getText().toString(), null);
                break;
            default:
                break;
        }
    }

    public interface OnFragmentInteractionListener {
    }
}
