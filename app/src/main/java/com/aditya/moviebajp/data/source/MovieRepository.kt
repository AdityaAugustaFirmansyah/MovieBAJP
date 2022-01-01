package com.aditya.moviebajp.data.source

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
        return remoteDataSource.getAllMovie().results.map {
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
                it.vote_average
            )
        }
    }

    override suspend fun getAllTv(): List<TvEntity> {
        return remoteDataSource.getAllTv().results.map {
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
                it.vote_average
            )
        }
    }

    override suspend fun getMovieById(id: String): MovieEntity {
        val response = remoteDataSource.getMovieById(id)
        return MovieEntity(
            response.poster_path,
            response.backdrop_path,
            response.overview,
            response.release_date,
            response.id,
            response.original_title,
            response.title,
            response.original_language,
            response.popularity,
            response.vote_count,
            response.adult,
            response.vote_average
        )
    }

    override suspend fun getTvById(id: String): TvEntity {
        val response = remoteDataSource.getTvById(id)
        return TvEntity(
            response.poster_path,
            response.backdrop_path,
            response.overview,
            response.first_air_date,
            response.id,
            response.original_name,
            response.name,
            response.original_language,
            response.popularity,
            response.vote_count,
            response.vote_average
        )
    }
}