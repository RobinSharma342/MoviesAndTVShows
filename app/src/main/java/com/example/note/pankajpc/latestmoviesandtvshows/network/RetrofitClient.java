package com.example.note.pankajpc.latestmoviesandtvshows.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pankaj PC on 01-29-2017.
 */

public class RetrofitClient {
    /********
     * URLS
     *******/
    private static final String ROOT_URL = "https://api.themoviedb.org/3/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
