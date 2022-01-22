package com.aditya.moviebajp.ui.favourite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity

class TvFavouriteViewModel(private val movieRepository: MovieRepository) :ViewModel() {
    fun getData():LiveData<PagedList<TvEntity>>{
        return movieRepository.getAllTvFavourite()
    }
}