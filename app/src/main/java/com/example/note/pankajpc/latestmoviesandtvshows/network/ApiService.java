package com.example.note.pankajpc.latestmoviesandtvshows.network;

import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pankaj PC on 01-29-2017.
 */

public interface ApiService {
   //@GET("movie/popular?api_key=55678da3e71af39e568440d6c13a4d3b&language=en-US&page=1")
   @GET("movie/now_playing")
   Call<TopRatedMoviesPojo> getnowPlaying(@Query("api_key") String apikey, @Query("language") String language,@Query("page") int page);
   @GET("movie/popular")
    Call<TopRatedMoviesPojo> getPopular(@Query("api_key") String apikey, @Query("language") String language,@Query("page") int page);
    @GET("movie/top_rated")
    Call<TopRatedMoviesPojo> getTopRated(@Query("api_key") String apikey, @Query("language") String language,@Query("page") int page);
    @GET("movie/upcoming")
    Call<TopRatedMoviesPojo> getUpcoming(@Query("api_key") String apikey, @Query("language") String language,@Query("page") int page);
}
