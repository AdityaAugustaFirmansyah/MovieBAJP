package com.aditya.moviebajp.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.vo.Resource

class DetailMovieViewModel(private val id:Int,private val movieRepository: MovieRepository):ViewModel() {

    fun detailMovieLiveData():LiveData<Resource<MovieEntity>>{
        return movieRepository.getMovieById(id.toString())
    }

    fun setFavourite(movieEntity: MovieEntity){
        movieEntity.favourite = !movieEntity.favourite
        movieRepository.updateMovie(movieEntity)
    }
}