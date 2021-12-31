package com.aditya.moviebajp.ui.tv

import androidx.lifecycle.ViewModel
import com.aditya.moviebajp.data.TvEntity
import com.aditya.moviebajp.utils.MovieDummy

class TvViewModel :ViewModel() {
    fun getData():List<TvEntity>{
        return MovieDummy.generateTv()
    }

    fun getData(index:Int):TvEntity{
        return MovieDummy.getDetailTv(index)
    }
}