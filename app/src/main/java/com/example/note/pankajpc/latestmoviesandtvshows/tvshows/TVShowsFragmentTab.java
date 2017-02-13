package com.example.note.pankajpc.latestmoviesandtvshows.tvshows;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.network.ApiService;
import com.example.note.pankajpc.latestmoviesandtvshows.network.RetrofitClient;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShows;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShowsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pankaj PC on 02-05-2017.
 */

public class TVShowsFragmentTab extends Fragment {
    RecyclerView recyclerView;
    Context context;
    LinearLayoutManager llm;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 7;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;
    List<TVShows> showsLists;
    String movieTypes = "";
    List<TVShows> tempList;
    private TVShowsAdapter tvShowsAdapter;

    public TVShowsFragmentTab() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        Bundle bundle = getArguments();
        movieTypes = bundle.getString("Type");


        View v = inflater.inflate(R.layout.fragment_movie_layout, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.main_list);
        llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = llm.getItemCount();
                firstVisibleItem = llm.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading
                        && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold) && current_page < 20) {
                    // End has been reached

                    // Do something
                    current_page++;
                    ApiService api = RetrofitClient.getApiService();
                    Call<TVShowsList> call = null;
                    if (movieTypes.equalsIgnoreCase("Now Playing")) {
                        call = api.getnowPlayingShows("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
                    } else if (movieTypes.equalsIgnoreCase("Popular Shows")) {
                        call = api.getPopularShows("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
                    } else if (movieTypes.equalsIgnoreCase("Top Rated Shows")) {
                        call = api.getTopRatedShows("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
                    }
                    call.enqueue(new Callback<TVShowsList>() {
                        @Override
                        public void onResponse(Call<TVShowsList> call, Response<TVShowsList> response) {
                            Log.i("response", "responsecode" + response);
                            tempList = response.body().getResults();
                            showsLists.addAll(tempList);
                            tvShowsAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<TVShowsList> call, Throwable t) {
                            Log.i("failure", "failure" + t.getLocalizedMessage());

                        }
                    });
                    loading = true;
                }

            }
        });
        loadJson();
        return v;
    }



    private void loadJson() {
        ApiService api = RetrofitClient.getApiService();
        Call<TVShowsList> call = null;
        if (movieTypes.equalsIgnoreCase("Now Playing")) {
            call = api.getnowPlayingShows("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
        } else if (movieTypes.equalsIgnoreCase("Popular Shows")) {
            call = api.getPopularShows("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
        } else if (movieTypes.equalsIgnoreCase("Top Rated Shows")) {
            call = api.getTopRatedShows("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
        }
        call.enqueue(new Callback<TVShowsList>() {
            @Override
            public void onResponse(Call<TVShowsList> call, Response<TVShowsList> response) {
                Log.i("response", "responsecode" + response);
                showsLists = response.body().getResults();
                tvShowsAdapter = new TVShowsAdapter(context, showsLists);
                recyclerView.setAdapter(tvShowsAdapter);
                tvShowsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TVShowsList> call, Throwable t) {
                Log.i("failure", "failure" + t.getLocalizedMessage());

            }
        });

    }





}
