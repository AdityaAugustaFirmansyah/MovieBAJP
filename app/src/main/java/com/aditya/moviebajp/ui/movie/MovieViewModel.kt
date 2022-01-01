package com.aditya.moviebajp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.data.MovieState
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.utils.MovieDummy
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MovieViewModel(private val movieRepository: MovieRepository) :ViewModel() {
    private val movieLiveData:MutableLiveData<MovieState> = MutableLiveData()

    init {
        movieLiveData.value = MovieState(ViewState.LOADING, mutableListOf(),"")
        viewModelScope.launch {
            try {
                movieLiveData.value = MovieState(ViewState.SUCCESS, movieRepository.getAllMovie(),"")
            }catch (e:HttpException){
                movieLiveData.value = MovieState(ViewState.FAILURE, mutableListOf(),e.message())
            }catch (e:IOException){
                movieLiveData.value =
                    e.message?.let { MovieState(ViewState.FAILURE, mutableListOf(), it) }
            }catch (e:Exception){
                movieLiveData.value =
                    e.message?.let { MovieState(ViewState.FAILURE, mutableListOf(), it) }
            }
        }
    }

    fun getData():LiveData<MovieState>{
        return movieLiveData
    }
}