package com.aditya.moviebajp.data.source.remote.response

data class DetailMovieResponse(
    val poster_path:String,
    val backdrop_path:String,
    val overview:String,
    val release_date:String,
    val id:Int,
    val original_title:String,
    val title:String,
    val original_language:String,
    val popularity:Double,
    val vote_count:Int,
    val adult:Boolean,
    val vote_average:Double,
)