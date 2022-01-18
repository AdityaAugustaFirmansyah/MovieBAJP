package com.aditya.moviebajp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.moviebajp.injection.Injection
import com.aditya.moviebajp.ui.detail.movie.DetailMovieViewModel
import com.aditya.moviebajp.ui.detail.tv.DetailTvViewModel

class DetailViewModelFactory(private val id:Int,private val context: Context) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(id,Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                DetailTvViewModel(id,Injection.provideRepository(context)) as T
            }
            else -> {
                throw Throwable("Unknown View Model")
            }
        }
    }
}