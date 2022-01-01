package com.aditya.moviebajp.data.source.remote

import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import com.aditya.moviebajp.data.source.remote.response.DetailTvResponse
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

    suspend fun getAllMovie():MovieResponse = restApi.getAllMovie()
    suspend fun getAllTv():TvResponse = restApi.getAllTv()
    suspend fun getMovieById(id:String):DetailMovieResponse = restApi.getMovieById(id)
    suspend fun getTvById(id:String):DetailTvResponse = restApi.getTvById(id)
}