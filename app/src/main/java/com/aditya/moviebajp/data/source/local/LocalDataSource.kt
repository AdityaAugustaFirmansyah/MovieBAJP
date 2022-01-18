package com.aditya.moviebajp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllMovie():DataSource.Factory<Int,MovieEntity> = movieDao.getAllMovies()
    fun getAllMovieAsc():DataSource.Factory<Int,MovieEntity> = movieDao.getAllMoviesAsc()
    fun getAllMovieDesc():DataSource.Factory<Int,MovieEntity> = movieDao.getAllMoviesDesc()
    fun insertMovies(movieList: MutableList<MovieEntity>) = movieDao.insertMovies(movieList)

    fun getAllMovieFavourite(): LiveData<List<MovieEntity>> = movieDao.getAllMoviesFavourite()
    fun getAllTvFavourite(): LiveData<List<TvEntity>> = movieDao.getAllTvsFavourite()

    fun getAllTv():DataSource.Factory<Int,TvEntity> = movieDao.getAllTvs()
    fun getAllTvAsc():DataSource.Factory<Int,TvEntity> = movieDao.getAllTvsAsc()
    fun getAllTvDesc():DataSource.Factory<Int,TvEntity> = movieDao.getAllTvsDesc()
    fun insertTvs(tvList: MutableList<TvEntity>) = movieDao.insertTvs(tvList)

    fun updateMovie(movieEntity: MovieEntity) = movieDao.updateMovie(movieEntity)
    fun updateTv(tvEntity: TvEntity) = movieDao.updateTv(tvEntity)

    fun getMovie(id: Int): LiveData<MovieEntity> = movieDao.getMovie(id)
    fun getTv(id: Int): LiveData<TvEntity> = movieDao.getTv(id)
}