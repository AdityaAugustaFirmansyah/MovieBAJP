package com.aditya.moviebajp.data

import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Status

class DetailTvState(
    val status: Status,
    val message:String,
    val response: TvEntity?
    )