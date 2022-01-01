package com.aditya.moviebajp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.injection.Injection
import com.aditya.moviebajp.network.RestApi
import com.aditya.moviebajp.ui.detail.movie.DetailMovieViewModel
import com.aditya.moviebajp.ui.detail.tv.DetailTvViewModel

class DetailViewModelFactory(private val id:Int,private val restApi:RestApi) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailMovieViewModel::class.java)){
            return DetailMovieViewModel(id,Injection.provideRepository(restApi)) as T
        }else if (modelClass.isAssignableFrom(DetailTvViewModel::class.java)){
            return DetailTvViewModel(id,Injection.provideRepository(restApi)) as T
        }else{
            throw Throwable("Unknow View Model")
        }
    }
}