package com.example.note.pankajpc.latestmoviesandtvshows;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.note.pankajpc.latestmoviesandtvshows.navigationdrawer.NavigationDrawerAdapter;
import com.example.note.pankajpc.latestmoviesandtvshows.navigationdrawer.NavigationDrawerModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<NavigationDrawerModel> navigationDrawerModelList = new ArrayList<>();
    ListView navigationLeftDrawer;
    DrawerLayout drawerLayout;
    Context context;
    Fragment fragment;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        context = this;
        initDrawerItem();
        initMovieFragmentData();


        navigationLeftDrawer = (ListView) findViewById(R.id.left_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //setting navigation adapter
        navigationLeftDrawer.setAdapter(new NavigationDrawerAdapter(context, R.layout.navigation_drawer_single_item, navigationDrawerModelList));
        navigationLeftDrawer.setOnItemClickListener(navigationDrawerItemClickListener);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation);

    }


    AdapterView.OnItemClickListener navigationDrawerItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            drawerLayout.closeDrawers();
            fragment = null;
            switch (navigationDrawerModelList.get(i).getNavDescription()) {
                case "Movies":
                    startActivity(new Intent(context, MainMovie.class));
                    break;
                case "Songs":
                    // startActivity(new Intent(context,MainSongs.class));
                    break;
                case "Celebrities":
                    // startActivity(new Intent(context,MainCelebrities.class));
                    break;
            }


        }
    };

    private void initDrawerItem() {
        navigationDrawerModelList.add(new NavigationDrawerModel("Movies", R.drawable.ic_movie));
        navigationDrawerModelList.add(new NavigationDrawerModel("Songs", R.drawable.ic_movie));
        navigationDrawerModelList.add(new NavigationDrawerModel("Celebrities", R.drawable.ic_movie));
    }


    private void initMovieFragmentData() {
        fragment = null;
        bundle = new Bundle();
        bundle.putString("Movies Type", "Now Playing Movies");
        fragment = new InitialMovieFragement();
        fragment.setArguments(bundle);
        setTitle(navigationDrawerModelList.get(0).getNavDescription());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder, fragment).commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                drawerLayout.openDrawer(Gravity.LEFT);
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
