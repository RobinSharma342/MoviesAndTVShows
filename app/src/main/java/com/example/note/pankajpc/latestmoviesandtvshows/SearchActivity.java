package com.example.note.pankajpc.latestmoviesandtvshows;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.note.pankajpc.latestmoviesandtvshows.celebrity.CelebDetail;
import com.example.note.pankajpc.latestmoviesandtvshows.celebrity.CelebrityAdapter;
import com.example.note.pankajpc.latestmoviesandtvshows.moviepackage.MovieAdapter;
import com.example.note.pankajpc.latestmoviesandtvshows.moviepackage.MovieDetail;
import com.example.note.pankajpc.latestmoviesandtvshows.network.ApiService;
import com.example.note.pankajpc.latestmoviesandtvshows.network.RetrofitClient;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.Celebrity;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.CelebrityList;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.PersonData;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShows;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShowsList;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesList;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesPojo;
import com.example.note.pankajpc.latestmoviesandtvshows.tvshows.TVShowsAdapter;
import com.example.note.pankajpc.latestmoviesandtvshows.tvshows.TVShowsDetail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private TextView mNoResultText;
    Context context;
    MovieAdapter movieAdapter;
    LinearLayoutManager llm;
    CelebrityAdapter celebAdapter;
    List<Celebrity> celebList;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 7;
    private GridLayoutManager glm;
    List<TVShows> showsLists;
    private TVShowsAdapter tvShowsAdapter;

    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;
    List<TopRatedMoviesList> movieList;
    String movieTypes;
    String query;
    String type;
    int j;

    List<TopRatedMoviesList> tempList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        context = this;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mNoResultText = (TextView) findViewById(R.id.noResultText);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
        if (appData != null) {
            type = appData.getString("type");
        }


        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            j = query.length();

            //use the query to search your data somehow
        }

        recyclerView = (RecyclerView) findViewById(R.id.main_list);
        llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);
        if (type.equalsIgnoreCase("movie")) {
            setTitle("Movies Search Results");
            loadMovieJson();
        } else if (type.equalsIgnoreCase("tvshows")) {
            setTitle("TV Shows Search Results");
            loadTVShowsJson();
        }
        else if (type.equalsIgnoreCase("celebrity")) {
            setTitle("Celebrity Search Results");
            loadCelebrityJson();
        }


    }

    private void loadTVShowsJson() {
        ApiService api = RetrofitClient.getApiService();
        Call<TVShowsList> call = null;
        call = api.searchTVShows("55678da3e71af39e568440d6c13a4d3b", "en-US", query, current_page);
        call.enqueue(new Callback<TVShowsList>() {
            @Override
            public void onResponse(Call<TVShowsList> call, Response<TVShowsList> response) {
                Log.i("response", "responsecode" + response);
                showsLists = response.body().getResults();
                checkTVShowApiResponse();
                tvShowsAdapter = new TVShowsAdapter(context, showsLists);
                recyclerView.setAdapter(tvShowsAdapter);
            }

            @Override
            public void onFailure(Call<TVShowsList> call, Throwable t) {
                Log.i("failure", "failure" + t.getLocalizedMessage());

            }
        });


    }

    private void checkTVShowApiResponse() {
        if (showsLists.isEmpty()) {
            if (query.length() == 1) {
                recyclerView.setVisibility(View.GONE);
                mNoResultText.setVisibility(View.VISIBLE);
            } else if (query.length() <= 0) {
                recyclerView.setVisibility(View.GONE);
                mNoResultText.setVisibility(View.VISIBLE);
            } else {
                query = query.substring(0, query.length() - 2);

                loadTVShowsJson();
            }
        }
    }

    private void loadCelebrityJson() {
        ApiService api = RetrofitClient.getApiService();
        Call<CelebrityList> call = null;
        call = api.searchPerson("55678da3e71af39e568440d6c13a4d3b", "en-US", query, current_page);
        call.enqueue(new Callback<CelebrityList>() {
            @Override
            public void onResponse(Call<CelebrityList> call, Response<CelebrityList> response) {
                celebList = response.body().getResults();
                checkCelebrityApiResponse();
                celebAdapter = new CelebrityAdapter(context,celebList);
                recyclerView = (RecyclerView) findViewById(R.id.main_list);
                glm = new GridLayoutManager(context, 2);
                recyclerView.setLayoutManager(glm);
                recyclerView.setAdapter(celebAdapter);

            }

            @Override
            public void onFailure(Call<CelebrityList> call, Throwable t) {
                Log.i("failure", "failure" + t.getLocalizedMessage());

            }
        });


    }

    private void checkCelebrityApiResponse() {
        if (celebList.isEmpty()) {
            if (query.length() == 1) {
                recyclerView.setVisibility(View.GONE);
                mNoResultText.setVisibility(View.VISIBLE);
            } else if (query.length() <= 0) {
                recyclerView.setVisibility(View.GONE);
                mNoResultText.setVisibility(View.VISIBLE);
            } else {
                query = query.substring(0, query.length() - 2);

                loadTVShowsJson();
            }
        }
    }

    private void loadMovieJson() {
        ApiService api = RetrofitClient.getApiService();
        Call<TopRatedMoviesPojo> call = null;
        call = api.searchMovies("55678da3e71af39e568440d6c13a4d3b", "en-US", query, current_page);
        call.enqueue(new Callback<TopRatedMoviesPojo>() {
            @Override
            public void onResponse(Call<TopRatedMoviesPojo> call, Response<TopRatedMoviesPojo> response) {
                movieList = response.body().getResults();

                movieAdapter = new MovieAdapter(context, movieList);
                recyclerView.setAdapter(movieAdapter);
                checkMovieApiResponse();
            }

            @Override
            public void onFailure(Call<TopRatedMoviesPojo> call, Throwable t) {
                Log.i("failure", "failure" + t.getLocalizedMessage());

            }
        });

    }

    private void checkMovieApiResponse() {
        if (movieList.isEmpty()) {
            if (query.length() == 1) {
                recyclerView.setVisibility(View.GONE);
                mNoResultText.setVisibility(View.VISIBLE);
            } else if (query.length() <= 0) {
                recyclerView.setVisibility(View.GONE);
                mNoResultText.setVisibility(View.VISIBLE);
            } else {
                query = query.substring(0, query.length() - 2);

                loadTVShowsJson();
            }
        }
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
    public void onEvent(TopRatedMoviesList topRatedMoviesList) {
        Intent i = new Intent(context, MovieDetail.class);
        i.putExtra("MovieDetailObject", topRatedMoviesList);
        startActivity(i);
    }

    @Subscribe
    public void onEvent(TVShows tvShows) {
        Intent i = new Intent(this, TVShowsDetail.class);
        i.putExtra("SongDetailObject", tvShows);
        startActivity(i);
    }

    @Subscribe
    public void onEvent(Celebrity celeb){
        loadJson(celeb.getId());

    }
    private void loadJson(int celebrityId) {
        int celebid = celebrityId;
        ApiService api = RetrofitClient.getApiService();
        Call<PersonData> call = null;
        call = api.getPersonData(celebid,"55678da3e71af39e568440d6c13a4d3b","en-US");
        call.enqueue(new Callback<PersonData>() {
            @Override
            public void onResponse(Call<PersonData> call, Response<PersonData> response) {

                PersonData personData = response.body();
                Intent i = new Intent(context, CelebDetail.class);
                i.putExtra("celebdetail",personData);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<PersonData> call, Throwable t) {
                Log.i("failure", "failure" + t.getLocalizedMessage());

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }
}

