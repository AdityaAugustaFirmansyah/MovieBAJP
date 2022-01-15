package com.aditya.moviebajp.data

import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Status


data class TvState(
    val tvs: List<TvEntity>,
    val msg: String,
    val status: Status
)