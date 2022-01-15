package com.aditya.moviebajp.data

import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.vo.Status

data class DetailMovieState(
    val status: Status,
    val message:String,
    val response: MovieEntity?
)