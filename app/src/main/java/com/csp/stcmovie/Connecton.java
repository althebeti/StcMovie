package com.csp.stcmovie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a-alt on 02/03/18.
 */

public class Connecton {

    public static final Retrofit retrofit = new Retrofit.Builder().
            baseUrl("http://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
