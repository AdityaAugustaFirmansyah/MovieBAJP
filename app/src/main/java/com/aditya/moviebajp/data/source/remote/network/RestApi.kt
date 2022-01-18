package com.aditya.moviebajp.data.source.remote.network

import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import com.aditya.moviebajp.data.source.remote.response.DetailTvResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {

    @GET("discover/movie")
    fun getAllMovie():Call<MovieResponse>

    @GET("discover/tv")
    fun getAllTv():Call<TvResponse>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id:String):Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun getTvById(@Path("id") id:String):Call<DetailTvResponse>
}