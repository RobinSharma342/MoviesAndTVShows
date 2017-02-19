package com.example.note.pankajpc.latestmoviesandtvshows.moviepackage;

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
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainMovie extends AppCompatActivity implements NoInternetDialog.DialogClickEvent {

    private ViewPager mPager;
    private TabLayout mTab;
    private String[] mPageTitle;
    private SearchView mSearchView;
    Context context;

    private String[] mMoviePageTitle = {"Now Playing", "Popular Movies", "Top Rated Movies", "Upcoming Movies"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_movie);
        context = this;
        setTitle("Movies");
        if(!CheckInternetConnection.checkInternet())
        {
            DialogFragment dialogFragment = new NoInternetDialog();
            dialogFragment.setCancelable(false);
            dialogFragment.show(getSupportFragmentManager(),"test");
        }
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setQueryHint("Search Movie..");
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        Bundle bundle = new Bundle();
        bundle.putString("type","movie");
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
