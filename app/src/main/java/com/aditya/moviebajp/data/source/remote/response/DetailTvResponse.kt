package com.aditya.moviebajp.data.source.remote.response

data class DetailTvResponse(
    val poster_path:String,
    val backdrop_path:String?,
    val overview:String,
    val first_air_date:String,
    val id:Int,
    val original_name:String,
    val name:String,
    val original_language:String,
    val popularity:Double,
    val vote_count:Int,
    val vote_average:Double
)
