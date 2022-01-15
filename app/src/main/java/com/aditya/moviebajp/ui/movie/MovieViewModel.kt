package com.aditya.moviebajp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.MovieState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val mutableLiveData: MutableLiveData<Resource<List<MovieEntity>>> =
        movieRepository.getAllMovie() as MutableLiveData<Resource<List<MovieEntity>>>

    fun getData(): LiveData<Resource<List<MovieEntity>>> {
        return mutableLiveData
    }

    fun sortingAsc(){
        mutableLiveData.value?.let {
            mutableLiveData.value = Resource.success(it.data?.let { it1 ->
                movieRepository.getAllMovieAscending(
                    it1
                )
            })
        }
    }

    fun sortingDesc(){
        mutableLiveData.value?.let {
            mutableLiveData.value = Resource.success(it.data?.let { it1 ->
                movieRepository.getAllMovieDesc(
                    it1
                )
            })
        }
    }
}