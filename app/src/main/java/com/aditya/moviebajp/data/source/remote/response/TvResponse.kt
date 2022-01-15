package com.aditya.moviebajp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(
    val results:List<DataTv>,
    val msg:String
)

data class DataTv(
    @SerializedName("poster_path")
    val posterPath:String,
    @SerializedName("backdrop_path")
    val backdropPath:String?,
    val overview:String,
    @SerializedName("first_air_date")
    val firstAirDate:String,
    val id:Int,
    @SerializedName("original_name")
    val originalName:String,
    val name:String,
    @SerializedName("original_language")
    val originalLanguage:String,
    val popularity:Double,
    @SerializedName("vote_count")
    val voteCount:Int,
    @SerializedName("vote_average")
    val voteAverage:Double
)
