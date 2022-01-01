package com.aditya.moviebajp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.injection.Injection
import com.aditya.moviebajp.network.RestApi
import com.aditya.moviebajp.ui.movie.MovieViewModel
import com.aditya.moviebajp.ui.tv.TvViewModel

class ViewModelFactory(private val movieRepository: MovieRepository):ViewModelProvider.Factory {
    companion object{
        @Volatile
        private var instance:ViewModelFactory?=null
        fun getInstance(restApi: RestApi)= instance?: synchronized(this){
            instance?:ViewModelFactory(Injection.provideRepository(restApi))
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java)->{
                MovieViewModel(movieRepository) as T
            }

            modelClass.isAssignableFrom(TvViewModel::class.java)->{
                TvViewModel(movieRepository) as T
            }

            else-> throw Throwable("Unknown View Model")
        }
    }
}