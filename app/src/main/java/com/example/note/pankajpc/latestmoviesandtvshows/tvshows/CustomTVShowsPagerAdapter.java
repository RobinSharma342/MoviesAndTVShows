package com.example.note.pankajpc.latestmoviesandtvshows.tvshows;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.note.pankajpc.latestmoviesandtvshows.tvshows.TVShowsFragmentTab;

/**
 * Created by Pankaj PC on 02-05-2017.
 */
public class CustomTVShowsPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mPageTitle;

    public CustomTVShowsPagerAdapter(FragmentManager fm, String[] mPageTitle) {
        super(fm);
        this.mPageTitle = mPageTitle;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        TVShowsFragmentTab ff = new TVShowsFragmentTab();
        switch (position) {
            case 0:
                bundle.putString("Type", "Now Playing");
                ff.setArguments(bundle);
                return ff;
            case 1:
                bundle.putString("Type", "Popular Shows");
                ff.setArguments(bundle);
                return ff;

            case 2:
                bundle.putString("Type", "Top Rated Shows");
                ff.setArguments(bundle);
                return ff;


            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitle[position];
    }
}
