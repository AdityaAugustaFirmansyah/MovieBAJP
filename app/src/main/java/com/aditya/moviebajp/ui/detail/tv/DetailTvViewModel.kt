package com.aditya.moviebajp.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.DetailTvState
import com.aditya.moviebajp.data.source.MovieRepository

class DetailTvViewModel(private val id:Int,private val movieRepository: MovieRepository):ViewModel() {
    fun detailTvLiveData():LiveData<DetailTvState>{
        return movieRepository.getTvById(id.toString())
    }
}