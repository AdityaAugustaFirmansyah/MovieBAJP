package com.aditya.moviebajp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results:List<MovieData>,
    val msg:String)

data class MovieData(
    @SerializedName("poster_path")
    val posterPath:String,
    @SerializedName("backdrop_path")
    val backdropPath:String,
    val overview:String,
    @SerializedName("release_date")
    val releaseDate:String,
    val id:Int,
    @SerializedName("original_title")
    val originalTitle:String,
    val title:String,
    @SerializedName("original_language")
    val originalLanguage:String,
    val popularity:Double,
    @SerializedName("vote_count")
    val voteCount:Int,
    val adult:Boolean,
    @SerializedName("vote_average")
    val voteAverage:Double
)
