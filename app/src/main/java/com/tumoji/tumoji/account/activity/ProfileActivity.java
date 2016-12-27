package com.tumoji.tumoji.account.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.contract.ProfileContract;
import com.tumoji.tumoji.account.fragment.ProfileInfoFragment;
import com.tumoji.tumoji.account.presenter.ProfilePresenter;
import com.tumoji.tumoji.account.view.ProfileFragment;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;

public class ProfileActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener, ProfileInfoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        ProfileFragment fragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = ProfileFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
        ProfileContract.Presenter presenter = new ProfilePresenter(MockAccountRepository.getInstance(this), fragment);
        fragment.setPresenter(presenter);
    }

    @Override
    public void onProfileInfoFragmentViewCreated() {
        ProfileFragment fragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.onProfileInfoFragmentViewCreated();
        }
    }
}
