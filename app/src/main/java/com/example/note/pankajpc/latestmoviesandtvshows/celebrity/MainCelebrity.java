package com.example.note.pankajpc.latestmoviesandtvshows.celebrity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.note.pankajpc.latestmoviesandtvshows.CheckInternetConnection;
import com.example.note.pankajpc.latestmoviesandtvshows.NoInternetDialog;
import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.network.ApiService;
import com.example.note.pankajpc.latestmoviesandtvshows.network.RetrofitClient;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.Celebrity;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.CelebrityList;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.PersonData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCelebrity extends AppCompatActivity implements NoInternetDialog.DialogClickEvent{
    RecyclerView recyclerView;
    Context context;
    CelebrityAdapter celebAdapter;
    GridLayoutManager glm;
    private int previousTotal = 0;
    private boolean loading = true;
    private SearchView mSearchView;
    private int visibleThreshold = 10;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;
    List<Celebrity> celebList;
    List<Celebrity> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_celebrity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Celebrities");

        context = this;
        if(!CheckInternetConnection.checkInternet())
        {
            DialogFragment dialogFragment = new NoInternetDialog();
            dialogFragment.setCancelable(false);
            dialogFragment.show(getSupportFragmentManager(),"test");
        }

        recyclerView = (RecyclerView) findViewById(R.id.main_list);
        glm = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(glm);



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = glm.getItemCount();
                firstVisibleItem = glm.findFirstVisibleItemPosition();

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
                    Call<CelebrityList> call = null;
                    call = api.getPopularPerson("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
                    call.enqueue(new Callback<CelebrityList>() {
                        @Override
                        public void onResponse(Call<CelebrityList> call, Response<CelebrityList> response) {
                            Log.i("response", "responsecode" + response);
                            tempList = response.body().getResults();
                            celebList.addAll(tempList);
                            celebAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<CelebrityList> call, Throwable t) {
                            Log.i("failure", "failure" + t.getLocalizedMessage());

                        }
                    });
                    loading = true;
                }

            }
        });

        loadJson();

    }

    private void loadJson() {
        ApiService api = RetrofitClient.getApiService();
        Call<CelebrityList> call = null;
        call = api.getPopularPerson("55678da3e71af39e568440d6c13a4d3b", "en-US", current_page);
        call.enqueue(new Callback<CelebrityList>() {
            @Override
            public void onResponse(Call<CelebrityList> call, Response<CelebrityList> response) {
                celebList = response.body().getResults();
                celebAdapter = new CelebrityAdapter(context,celebList);
                recyclerView.setAdapter(celebAdapter);
            }

            @Override
            public void onFailure(Call<CelebrityList> call, Throwable t) {
                Log.i("failure", "failure" + t.getLocalizedMessage());

            }
        });

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

    public boolean onCreateOptionsMenu(final Menu menu) {


        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setQueryHint("Search Celebrity..");
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        Bundle bundle = new Bundle();
        bundle.putString("type","celebrity");
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
