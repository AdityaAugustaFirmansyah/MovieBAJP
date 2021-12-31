package com.aditya.moviebajp.data.source.remote

import com.aditya.moviebajp.data.source.remote.response.GenreResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse
import com.aditya.moviebajp.network.RestApi

class RemoteDataSource private constructor(private val restApi: RestApi) {
    companion object{
        @Volatile
        private var instance:RemoteDataSource?=null
        fun getInstance(restApi: RestApi):RemoteDataSource = instance?: synchronized(this){
            instance?:RemoteDataSource(restApi)
        }
    }

    suspend fun getAllMovie():List<MovieResponse>{
        return restApi.getAllMovie().apply {

        }
    }
    suspend fun getAllTv():List<TvResponse> = restApi.getAllTv()
    suspend fun getAllGenre():List<GenreResponse> = restApi.getGenreMovies()
}