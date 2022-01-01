package com.aditya.moviebajp.injection

import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.network.RestApi

object Injection {
    fun provideRepository(apiClient: RestApi):MovieRepository{
        val remoteDataSource = RemoteDataSource.getInstance(apiClient)
        return MovieRepository.getInstance(remoteDataSource)
    }
}