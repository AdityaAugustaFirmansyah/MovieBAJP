package com.aditya.moviebajp.data

import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.vo.Status


data class MovieState(
    val status: Status,
    val success:List<MovieEntity>,
    val msg:String
)