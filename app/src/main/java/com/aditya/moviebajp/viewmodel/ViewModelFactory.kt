package com.aditya.moviebajp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.injection.Injection
import com.aditya.moviebajp.ui.favourite.movie.MovieFavouriteViewModel
import com.aditya.moviebajp.ui.favourite.tv.TvFavouriteViewModel
import com.aditya.moviebajp.ui.movie.MovieViewModel
import com.aditya.moviebajp.ui.tv.TvViewModel

class ViewModelFactory(private val movieRepository: MovieRepository):ViewModelProvider.Factory {
    companion object{
        @Volatile
        private var instance:ViewModelFactory?=null
        fun getInstance(context: Context)= instance?: synchronized(this){
            instance?:ViewModelFactory(Injection.provideRepository(context))
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

            modelClass.isAssignableFrom(MovieFavouriteViewModel::class.java)->{
                MovieFavouriteViewModel(movieRepository) as T
            }

            modelClass.isAssignableFrom(TvFavouriteViewModel::class.java)->{
                TvFavouriteViewModel(movieRepository) as T
            }
            else-> throw Throwable("Unknown View Model")
        }
    }
}