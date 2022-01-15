package com.aditya.moviebajp.injection

import android.content.Context
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.LocalDataSource
import com.aditya.moviebajp.data.source.local.room.DatabaseLocal
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.network.ApiClient
import com.aditya.moviebajp.network.RestApi
import com.aditya.moviebajp.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context):MovieRepository{
        val remoteDataSource = RemoteDataSource.getInstance(ApiClient.restApi())
        val database = DatabaseLocal.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val executors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource,localDataSource,executors)
    }
}