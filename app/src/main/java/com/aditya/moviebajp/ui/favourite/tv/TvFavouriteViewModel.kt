package com.aditya.moviebajp.ui.favourite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.TvState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Resource

class TvFavouriteViewModel(private val movieRepository: MovieRepository) :ViewModel() {
    fun getData():LiveData<List<TvEntity>>{
        return movieRepository.getAllTvFavourite()
    }
}