package com.aditya.moviebajp.data.source

import com.aditya.moviebajp.data.MovieEntity
import com.aditya.moviebajp.data.TvEntity

interface MovieDataSource {
    suspend fun getAllMovie():List<MovieEntity>
    suspend fun getAllTv():List<TvEntity>
    suspend fun getMovieById(id:String):MovieEntity
    suspend fun getTvById(id:String):TvEntity
}