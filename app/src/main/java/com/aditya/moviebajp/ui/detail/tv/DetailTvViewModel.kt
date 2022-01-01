package com.aditya.moviebajp.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.moviebajp.data.DetailTvState
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.data.source.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailTvViewModel(private val id:Int, private val movieRepository: MovieRepository):ViewModel() {
    private val detailTvLiveData:MutableLiveData<DetailTvState> = MutableLiveData()
    init {
        detailTvLiveData.value = DetailTvState(ViewState.LOADING,"",null)
        viewModelScope.launch {
            try {
                detailTvLiveData.value = DetailTvState(ViewState.SUCCESS,"",movieRepository.getTvById(id.toString()))
            }catch (e:HttpException){
                detailTvLiveData.value = DetailTvState(ViewState.FAILURE,"${e.message} ${e.code()}", null)
            }catch (e:IOException){
                detailTvLiveData.value = DetailTvState(ViewState.FAILURE,"${e.message}", null)
            }catch (e:Exception){
                detailTvLiveData.value = DetailTvState(ViewState.FAILURE,"${e.message}", null)
            }
        }
    }

    fun detailTvLiveData():LiveData<DetailTvState>{
        return detailTvLiveData
    }
}