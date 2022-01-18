package com.aditya.moviebajp.ui.favourite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.MovieEntity

class MovieFavouriteViewModel(private val movieRepository: MovieRepository) :ViewModel() {

    fun getData():LiveData<List<MovieEntity>>{
        return movieRepository.getAllMovieFavourite()
    }
}