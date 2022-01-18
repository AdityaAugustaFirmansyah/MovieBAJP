package com.aditya.moviebajp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies():DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM MovieEntity ORDER BY title ASC")
    fun getAllMoviesAsc():DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM MovieEntity ORDER BY title DESC")
    fun getAllMoviesDesc():DataSource.Factory<Int,MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieList: MutableList<MovieEntity>)

    @Query("SELECT * FROM TvEntity")
    fun getAllTvs():DataSource.Factory<Int,TvEntity>

    @Query("SELECT * FROM TvEntity ORDER BY name ASC")
    fun getAllTvsAsc():DataSource.Factory<Int,TvEntity>

    @Query("SELECT * FROM TvEntity ORDER BY name DESC")
    fun getAllTvsDesc():DataSource.Factory<Int,TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(movieList: MutableList<TvEntity>)

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Update
    fun updateTv(tvEntity: TvEntity)

    @Query("SELECT * FROM TvEntity WHERE id = :id")
    fun getTv(id:Int):LiveData<TvEntity>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovie(id:Int):LiveData<MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE favourite = 1")
    fun getAllMoviesFavourite():LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvEntity WHERE favourite = 1")
    fun getAllTvsFavourite():LiveData<List<TvEntity>>
}