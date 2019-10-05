package com.udacity.sandwichclub;

import retrofit2.http.GET;

/**
 * @author ihsan on 2019-10-05.
 */
interface Api {

    @GET("/movie/popular")
    void getPopularMovies();
}
