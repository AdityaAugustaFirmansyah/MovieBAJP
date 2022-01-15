package com.aditya.moviebajp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    val posterPath:String,
    val backdropPath:String,
    val overview:String,
    val releaseDate:String,
    @PrimaryKey
    val id:Int,
    val originalTitle:String,
    val title:String,
    val originalLanguage:String,
    val popularity:Double,
    var favourite:Boolean,
    val voteCount:Int,
    val adult:Boolean,
    val voteAverage:Double)
