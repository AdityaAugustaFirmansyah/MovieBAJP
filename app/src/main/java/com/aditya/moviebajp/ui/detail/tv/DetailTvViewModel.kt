package com.aditya.moviebajp.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Resource

class DetailTvViewModel(private val id: Int, private val movieRepository: MovieRepository) :
    ViewModel() {

    fun detailTvLiveData(): LiveData<Resource<TvEntity>> {
        return movieRepository.getTvById(id.toString())
    }

    fun setFavourite(tvEntity: TvEntity) {
        tvEntity.favourite = !tvEntity.favourite
        movieRepository.updateTv(tvEntity)
    }
}