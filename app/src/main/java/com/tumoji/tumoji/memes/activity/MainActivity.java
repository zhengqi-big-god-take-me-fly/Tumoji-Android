package com.tumoji.tumoji.memes.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.account.view.SignInSignUpFragment;
import com.tumoji.tumoji.data.account.repository.MockAccountRepository;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.meme.repository.MockMemeRepository;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.data.tag.repository.MockTagRepository;
import com.tumoji.tumoji.memes.contract.MemesContract;
import com.tumoji.tumoji.memes.fragment.MemesListFragment;
import com.tumoji.tumoji.memes.view.MoreTagsFragment;
import com.tumoji.tumoji.memes.presenter.MemesPresenter;
import com.tumoji.tumoji.memes.view.MemesFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MemesListFragment.OnFragmentInteractionListener, MoreTagsFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MemesFragment memesFragment = (MemesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (memesFragment == null) {
            memesFragment = MemesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, memesFragment).commit();
        }

        MemesContract.Presenter presenter = new MemesPresenter(MockAccountRepository.getInstance(), MockMemeRepository.getInstance(), MockTagRepository.getInstance(this), memesFragment);
        memesFragment.setPresenter(presenter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        MemesFragment memesFragment = (MemesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (memesFragment != null) {
            memesFragment.onNavigationItemSelected(item);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentViewCreated(int index) {
        MemesFragment memesFragment = (MemesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (memesFragment != null) {
            memesFragment.onListFragmentViewCreated(index);
        }
    }

    @Override
    public void onRefreshMemesList(int index) {
        MemesFragment memesFragment = (MemesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (memesFragment != null) {
            memesFragment.onRefreshMemesList(index);
        }
    }

    @Override
    public void onLoadMore(int index, int offset) {
        MemesFragment memesFragment = (MemesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (memesFragment != null) {
            memesFragment.onLoadMore(index, offset);
        }
    }

    @Override
    public void onMemeClick(MemeModel memeModel) {
        MemesFragment memesFragment = (MemesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (memesFragment != null) {
            memesFragment.onMemeClick(memeModel);
        }
    }

    @Override
    public void onTagClick(TagModel tagModel) {
        MemesFragment memesFragment = (MemesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (memesFragment != null) {
            memesFragment.onTagInMoreTagsPageClick(tagModel);
        }
    }
}
