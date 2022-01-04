package com.aditya.moviebajp.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.moviebajp.data.TvEntity
import com.aditya.moviebajp.data.TvState
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.utils.MovieDummy
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class TvViewModel(private val movieRepository: MovieRepository) :ViewModel() {
    fun getData():LiveData<TvState>{
        return movieRepository.getAllTv()
    }
}