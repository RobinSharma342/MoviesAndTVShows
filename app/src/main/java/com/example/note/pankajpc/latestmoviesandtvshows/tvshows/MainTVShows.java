package com.example.note.pankajpc.latestmoviesandtvshows.tvshows;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.note.pankajpc.latestmoviesandtvshows.CheckInternetConnection;
import com.example.note.pankajpc.latestmoviesandtvshows.NoInternetDialog;
import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShows;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainTVShows extends AppCompatActivity implements NoInternetDialog.DialogClickEvent {

    private ViewPager mPager;
    private TabLayout mTab;
    private String[] mPageTitle;
    private SearchView mSearchView;
    private String[] mSongPageTitle = {"Now Playing", "Popular Shows", "Top Rated Shows"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_movie);
        setTitle("TV Shows");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!CheckInternetConnection.checkInternet())
        {
            DialogFragment dialogFragment = new NoInternetDialog();
            dialogFragment.setCancelable(false);
            dialogFragment.show(getSupportFragmentManager(),"test");
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new CustomTVShowsPagerAdapter(getSupportFragmentManager(), mSongPageTitle));
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(TVShows tvShows){
        Intent i = new Intent(this, TVShowsDetail.class);
        i.putExtra("SongDetailObject", tvShows);
        startActivity(i);
    }

    public boolean onCreateOptionsMenu(final Menu menu) {


        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setQueryHint("Search TV Show..");
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        Bundle bundle = new Bundle();

        bundle.putString("type","tvshows");
        mSearchView.setAppSearchData(bundle);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MenuItemCompat.collapseActionView(menuItem);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;

    }

    @Override
    public void onPositiveButtonClick() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onNegativeButtonClick() {

    }
}
