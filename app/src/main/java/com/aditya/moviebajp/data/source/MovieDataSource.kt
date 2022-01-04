package com.aditya.moviebajp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aditya.moviebajp.data.*

interface MovieDataSource {
    fun getAllMovie():LiveData<MovieState>
    fun getAllTv():MutableLiveData<TvState>
    fun getMovieById(id:String):MutableLiveData<DetailMovieState>
    fun getTvById(id:String):MutableLiveData<DetailTvState>
}