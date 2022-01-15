package com.aditya.moviebajp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies():LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieList: MutableList<MovieEntity>)

    @Query("SELECT * FROM TvEntity")
    fun getAllTvs():LiveData<List<TvEntity>>

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

    @Query("SELECT * FROM tventity WHERE favourite = 1")
    fun getAllTvsFavourite():LiveData<List<TvEntity>>
}