package com.aditya.moviebajp.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.vo.Resource

class TvViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getData(sort:String): LiveData<Resource<PagedList<TvEntity>>> {
        return movieRepository.getAllTv(sort)
    }
}