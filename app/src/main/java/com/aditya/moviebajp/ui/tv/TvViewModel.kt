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
    private val tvLiveData:MutableLiveData<TvState> = MutableLiveData()

    init {
        tvLiveData.value = TvState(mutableListOf(),"",ViewState.LOADING)
        viewModelScope.launch {
            try {
                tvLiveData.value = TvState(movieRepository.getAllTv(),"",ViewState.SUCCESS)
            }catch (e: HttpException){
                tvLiveData.value = TvState( mutableListOf(),e.message(),ViewState.FAILURE)
                e.printStackTrace()
            }catch (e: IOException){
                tvLiveData.value =
                    e.message?.let { TvState( mutableListOf(), it,ViewState.FAILURE) }
                e.printStackTrace()
            }catch (e:Exception){
                tvLiveData.value =
                    e.message?.let { TvState(mutableListOf(), it,ViewState.FAILURE) }
                e.printStackTrace()
            }
        }
    }

    fun getData():LiveData<TvState>{
        return tvLiveData
    }

    fun getData(index:Int):TvEntity{
        return MovieDummy.getDetailTv(index)
    }
}