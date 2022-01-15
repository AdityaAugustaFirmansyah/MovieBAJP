package com.aditya.moviebajp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvEntity(
    val posterPath: String,
    val backdropPath: String?,
    val overview: String,
    val firstAirDate: String,
    @PrimaryKey
    val id: Int,
    val originalName: String,
    val name: String,
    val originalLanguage: String,
    var favourite:Boolean,
    val popularity: Double,
    val voteCount: Int,
    val voteAverage: Double
)
