package com.aditya.moviebajp.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.TvState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Resource

class TvViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val mutableLiveData: MutableLiveData<Resource<List<TvEntity>>> =
        movieRepository.getAllTv() as MutableLiveData<Resource<List<TvEntity>>>

    fun getData(): LiveData<Resource<List<TvEntity>>> {
        return mutableLiveData
    }

    fun sortingAsc() {
        mutableLiveData.value?.let {
            mutableLiveData.value = Resource.success(it.data?.let { it1 ->
                movieRepository.getAllTvAscending(
                    it1
                )
            })
        }
    }

    fun sortingDesc() {
        mutableLiveData.value?.let {
            mutableLiveData.value = Resource.success(it.data?.let { it1 ->
                movieRepository.getAllTvDesc(
                    it1
                )
            })
        }
    }
}