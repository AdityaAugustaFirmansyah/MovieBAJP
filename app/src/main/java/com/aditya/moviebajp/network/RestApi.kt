package com.aditya.moviebajp.network

import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import com.aditya.moviebajp.data.source.remote.response.DetailTvResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {

    @GET("discover/movie")
    suspend fun getAllMovie():MovieResponse

    @GET("discover/tv")
    suspend fun getAllTv():TvResponse

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id:String):DetailMovieResponse

    @GET("tv/{id}")
    suspend fun getTvById(@Path("id") id:String):DetailTvResponse
}