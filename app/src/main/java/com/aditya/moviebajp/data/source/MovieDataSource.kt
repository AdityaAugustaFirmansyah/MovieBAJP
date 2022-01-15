package com.aditya.moviebajp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aditya.moviebajp.data.*
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Resource

interface MovieDataSource {
    fun getAllMovie():LiveData<Resource<List<MovieEntity>>>
    fun getAllTv():LiveData<Resource<List<TvEntity>>>
    fun getMovieById(id:String):LiveData<Resource<MovieEntity>>
    fun getTvById(id:String):LiveData<Resource<TvEntity>>
    fun updateMovie(movieEntity: MovieEntity)
    fun updateTv(tvEntity: TvEntity)
    fun getAllMovieFavourite(): LiveData<List<MovieEntity>>
    fun getAllTvFavourite(): LiveData<List<TvEntity>>
    fun getAllMovieAscending(movies:List<MovieEntity>):List<MovieEntity>
    fun getAllMovieDesc(movies: List<MovieEntity>): List<MovieEntity>
    fun getAllTvAscending(movies: List<TvEntity>): List<TvEntity>
    fun getAllTvDesc(movies: List<TvEntity>): List<TvEntity>
}