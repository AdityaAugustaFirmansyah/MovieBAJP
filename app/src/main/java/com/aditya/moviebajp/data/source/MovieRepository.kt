package com.aditya.moviebajp.data.source

import androidx.lifecycle.LiveData
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.data.TvEntity
import com.aditya.moviebajp.data.source.remote.RemoteDataSource

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource)
            }
    }

    override suspend fun getAllMovie(): List<MovieEntity> {
        return remoteDataSource.getAllMovie().map {
            MovieEntity(
                it.poster_path,
                it.backdrop_path,
                it.overview,
                it.release_date,
                it.id,
                it.original_title,
                it.title,
                it.original_language,
                it.popularity,
                it.vote_count,
                it.adult,
                it.genre_name.map { String() },
                it.vote_average
            )
        }
    }

    override suspend fun getAllTv(): List<TvEntity> {
        return remoteDataSource.getAllTv().map {
            TvEntity(
                it.poster_path,
                it.backdrop_path,
                it.overview,
                it.first_air_date,
                it.id,
                it.original_name,
                it.name,
                it.original_language,
                it.popularity,
                it.vote_count,
                it.genre_name,
                it.vote_average
            )
        }
    }
}