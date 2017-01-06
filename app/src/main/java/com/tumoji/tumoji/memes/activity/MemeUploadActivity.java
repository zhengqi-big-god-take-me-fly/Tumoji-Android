package com.tumoji.tumoji.memes.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.repository.MockMemeRepository;
import com.tumoji.tumoji.data.tag.repository.TagRepository;
import com.tumoji.tumoji.memes.adapter.SelectTagsRecyclerAdapter;
import com.tumoji.tumoji.memes.contract.MemeUploadContract;
import com.tumoji.tumoji.memes.presenter.MemeUploadPresenter;
import com.tumoji.tumoji.memes.view.MemeUploadFragment;
import com.tumoji.tumoji.memes.view.SelectTagsFragment;

import java.util.List;

public class MemeUploadActivity extends AppCompatActivity implements MemeUploadFragment.OnFragmentInteractionListener, SelectTagsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        }

        MemeUploadFragment fragment = (MemeUploadFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = MemeUploadFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        MemeUploadContract.Presenter presenter = new MemeUploadPresenter(MockMemeRepository.getInstance(this), TagRepository.getInstance(), fragment);

        fragment.setPresenter(presenter);
    }

    @Override
    public void onSelectTagsFragmentCreateView() {
        MemeUploadFragment fragment = (MemeUploadFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.onSelectedTagsFragmentCreateView();
        }
    }

    @Override
    public void onFinishSelection(List<SelectTagsRecyclerAdapter.TagModelSelectableWrapper> tagModels) {
        MemeUploadFragment fragment = (MemeUploadFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.onFinishSelection(tagModels);
        }
    }
}
