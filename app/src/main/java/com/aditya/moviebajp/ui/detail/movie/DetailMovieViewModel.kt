package com.aditya.moviebajp.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.moviebajp.data.DetailMovieState
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailMovieViewModel(private val id:Int,private val movieRepository: MovieRepository):ViewModel() {

    fun detailMovieLiveData():LiveData<DetailMovieState>{
        return movieRepository.getMovieById(id.toString())
    }
}