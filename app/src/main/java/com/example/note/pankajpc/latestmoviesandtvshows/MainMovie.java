package com.example.note.pankajpc.latestmoviesandtvshows;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
}
