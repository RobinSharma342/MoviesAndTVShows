package com.example.note.pankajpc.latestmoviesandtvshows.moviepackage;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainMovie extends AppCompatActivity {

    private ViewPager mPager;
    private TabLayout mTab;
    private String[] mPageTitle;
    private SearchView mSearchView;

    private String[] mMoviePageTitle = {"Now Playing", "Popular Movies", "Top Rated Movies", "Upcoming Movies"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_movie);
        setTitle("Movies");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new CustomMoviePagerAdapter(getSupportFragmentManager(), mMoviePageTitle));

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
    public void onEvent(TopRatedMoviesList topRatedMoviesList){
        Intent i = new Intent(this, MovieDetail.class);
        i.putExtra("MovieDetailObject", topRatedMoviesList);
        startActivity(i);
    }



    public boolean onCreateOptionsMenu(final Menu menu) {


        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));



        return true;

    }
}
