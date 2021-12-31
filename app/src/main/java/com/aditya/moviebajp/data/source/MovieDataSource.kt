package com.aditya.moviebajp.data.source

import androidx.lifecycle.LiveData
import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.data.TvEntity

interface MovieDataSource {
    suspend fun getAllMovie():List<MovieEntity>
    suspend fun getAllTv():List<TvEntity>
}