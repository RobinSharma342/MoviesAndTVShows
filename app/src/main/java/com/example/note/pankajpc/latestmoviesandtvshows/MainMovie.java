package com.example.note.pankajpc.latestmoviesandtvshows;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainMovie extends AppCompatActivity {

    private ViewPager mPager;
    private TabLayout mTab;
    private String[] mPageTitle = {"Now Playing", "Popular Movies", "Top Rated Movies", "Upcoming Movies"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_movie);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager(), mPageTitle));
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


}
