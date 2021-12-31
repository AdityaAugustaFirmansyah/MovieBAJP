package com.aditya.moviebajp.network

import com.aditya.moviebajp.data.source.remote.response.GenreResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse
import retrofit2.http.GET

interface RestApi {

    @GET("discover/movie")
    suspend fun getAllMovie():List<MovieResponse>

    @GET("discover/tv")
    suspend fun getAllTv():List<TvResponse>

    @GET("genre/movie/list")
    suspend fun getGenreMovies():List<GenreResponse>
}