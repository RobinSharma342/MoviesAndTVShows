package com.example.note.pankajpc.latestmoviesandtvshows.network;

import com.example.note.pankajpc.latestmoviesandtvshows.pojo.CelebrityList;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.PersonData;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShowsList;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pankaj PC on 01-29-2017.
 */

public interface ApiService {
    //@GET("movie/popular?api_key=55678da3e71af39e568440d6c13a4d3b&language=en-US&page=1")
    @GET("movie/now_playing")
    Call<TopRatedMoviesPojo> getnowPlaying(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("movie/popular")
    Call<TopRatedMoviesPojo> getPopular(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("movie/top_rated")
    Call<TopRatedMoviesPojo> getTopRated(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("movie/upcoming")
    Call<TopRatedMoviesPojo> getUpcoming(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("tv/on_the_air")
    Call<TVShowsList> getnowPlayingShows(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("tv/popular")
    Call<TVShowsList> getPopularShows(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("tv/top_rated")
    Call<TVShowsList> getTopRatedShows(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("search/movie")
    Call<TopRatedMoviesPojo> searchMovies(@Query("api_key") String apikey, @Query("language") String language, @Query("query") String query,@Query("page") int page);

    @GET("search/tv")
    Call<TVShowsList> searchTVShows(@Query("api_key") String apikey, @Query("language") String language, @Query("query") String query,@Query("page") int page);

    @GET("search/person")
    Call<CelebrityList> searchPerson(@Query("api_key") String apikey, @Query("language") String language, @Query("query") String query,@Query("page") int page);

    @GET("person/popular")
    Call<CelebrityList> getPopularPerson(@Query("api_key") String apikey, @Query("language") String language, @Query("page") int page);

    @GET("person/{person_id}")
    Call<PersonData> getPersonData(@Path("person_id") int personid, @Query("api_key") String apikey, @Query("language") String language);

}
