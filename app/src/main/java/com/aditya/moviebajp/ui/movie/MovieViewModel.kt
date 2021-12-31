package com.aditya.moviebajp.ui.movie

import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.utils.MovieDummy

class MovieViewModel :ViewModel() {
    fun getData():List<MovieEntity>{
        return MovieDummy.generateMovies()
    }

    fun getData(index:Int):MovieEntity{
        return MovieDummy.getDetailMovie(index)
    }
}