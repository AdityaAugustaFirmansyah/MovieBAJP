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

class DetailMovieViewModel(private val id:Int, private val movieRepository: MovieRepository):ViewModel() {
    private val detailMovieLiveData:MutableLiveData<DetailMovieState> = MutableLiveData()
    init {
        detailMovieLiveData.value = DetailMovieState(ViewState.LOADING,"",null)
        viewModelScope.launch {
            try {
                detailMovieLiveData.value = DetailMovieState(ViewState.SUCCESS,"",movieRepository.getMovieById(id.toString()))
            }catch (e:HttpException){
                detailMovieLiveData.value = DetailMovieState(ViewState.FAILURE,"${e.message} ${e.code()}", null)
            }catch (e:IOException){
                detailMovieLiveData.value = DetailMovieState(ViewState.FAILURE,"${e.message}", null)
            }catch (e:Exception){
                detailMovieLiveData.value = DetailMovieState(ViewState.FAILURE,"${e.message}", null)
            }
        }
    }

    fun detailMovieLiveData():LiveData<DetailMovieState>{
        return detailMovieLiveData
    }
}