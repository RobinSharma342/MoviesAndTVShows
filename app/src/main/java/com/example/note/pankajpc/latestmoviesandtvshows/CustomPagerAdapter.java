package com.example.note.pankajpc.latestmoviesandtvshows;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Pankaj PC on 02-05-2017.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mPageTitle;

    public CustomPagerAdapter(FragmentManager fm, String[] mPageTitle) {
        super(fm);
        this.mPageTitle = mPageTitle;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        MovieFragmentTab ff = new MovieFragmentTab();
        switch (position) {
            case 0:
                bundle.putString("Type", "Now Playing Movies");
                ff.setArguments(bundle);
                return ff;
            case 1:
                bundle.putString("Type", "Popular Movies");
                ff.setArguments(bundle);
                return ff;

            case 2:
                bundle.putString("Type", "Top Rated Movies");
                ff.setArguments(bundle);
                return ff;
            case 3:
                bundle.putString("Type", "Upcoming Movies");
                ff.setArguments(bundle);
                return ff;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitle[position];
    }
}
