package com.aditya.moviebajp.data

data class DetailMovieState(
    val viewState: ViewState,
    val message:String,
    val response: MovieEntity?
)