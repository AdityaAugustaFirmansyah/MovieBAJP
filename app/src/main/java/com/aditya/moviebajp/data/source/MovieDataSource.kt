package com.aditya.moviebajp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Resource

interface MovieDataSource {
    fun getAllMovie(sort:String):LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllTv(sort: String):LiveData<Resource<PagedList<TvEntity>>>
    fun getMovieById(id:String):LiveData<Resource<MovieEntity>>
    fun getTvById(id:String):LiveData<Resource<TvEntity>>
    fun updateMovie(movieEntity: MovieEntity)
    fun updateTv(tvEntity: TvEntity)
    fun getAllMovieFavourite(): LiveData<List<MovieEntity>>
    fun getAllTvFavourite(): LiveData<List<TvEntity>>
}