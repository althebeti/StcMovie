package com.csp.stcmovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by a-alt on 01/03/18.
 */

public interface API {


    @GET("/3/movie/popular")
    Call<PopularMovieResponse> getpopular(@Query("api_key") String API_KEY,
                                        @Query("language") String language,
                                        @Query("page") int page ,
                                        @Query("region") String region );




    @GET("/3/search/movie")
    Call<PopularMovieResponse> getSearchedMovies(@Query("api_key") String API_KEY,
                                            @Query("query") String movie,
                                            @Query("page") int page,
                                            @Query("language") String lang);




}
