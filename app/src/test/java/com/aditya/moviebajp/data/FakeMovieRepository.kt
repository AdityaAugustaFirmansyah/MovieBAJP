package com.aditya.moviebajp.data

import androidx.lifecycle.MutableLiveData
import com.aditya.moviebajp.data.source.MovieDataSource
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import com.aditya.moviebajp.data.source.remote.response.DetailTvResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {
    override fun getAllMovie(): MutableLiveData<MovieState> {
        val mutableLiveData = MutableLiveData<MovieState>()
        mutableLiveData.value = MovieState(ViewState.LOADING, mutableListOf(), "")
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onSuccess(movieResponse: MovieResponse) {
                mutableLiveData.value = MovieState(ViewState.SUCCESS,
                    movieResponse.results.map {
                        MovieEntity(
                            it.poster_path,
                            it.backdrop_path,
                            it.overview,
                            it.release_date,
                            it.id,
                            it.original_title,
                            it.title,
                            it.original_language,
                            it.popularity,
                            it.vote_count,
                            it.adult,
                            it.vote_average
                        )
                    }, ""
                )
            }

            override fun onFailure(msg:String) {
                mutableLiveData.value = MovieState(ViewState.FAILURE, mutableListOf(), msg)
            }
        })
        return mutableLiveData
    }

    override fun getAllTv(): MutableLiveData<TvState> {
        val mutableLiveData = MutableLiveData<TvState>()
        mutableLiveData.value = TvState(mutableListOf(), "", ViewState.LOADING)
        remoteDataSource.getAllTv(object : RemoteDataSource.LoadTvCallback{
            override fun onSuccess(tvResponse: TvResponse) {
                mutableLiveData.value = tvResponse.results.map {
                    TvEntity(
                        it.poster_path,
                        it.backdrop_path,
                        it.overview,
                        it.first_air_date,
                        it.id,
                        it.original_name,
                        it.name,
                        it.original_language,
                        it.popularity,
                        it.vote_count,
                        it.vote_average
                    )
                }.let { TvState(it, "", ViewState.SUCCESS) }
            }

            override fun onFailure(msg: String) {
                mutableLiveData.value = TvState(mutableListOf(), msg,ViewState.FAILURE)
            }

        })
        return mutableLiveData
    }

    override fun getMovieById(id: String): MutableLiveData<DetailMovieState> {
        val mutableLiveData = MutableLiveData<DetailMovieState>()
        mutableLiveData.value = DetailMovieState(ViewState.LOADING, "", null)
        remoteDataSource.getMovieById(id, object : RemoteDataSource.LoadDetailMovieCallback{
            override fun onSuccess(detailMovieResponse: DetailMovieResponse) {
                detailMovieResponse.let {
                    mutableLiveData.value = DetailMovieState(
                        ViewState.SUCCESS, "", MovieEntity(
                            it.poster_path,
                            it.backdrop_path,
                            it.overview,
                            it.release_date,
                            it.id,
                            it.original_title,
                            it.title,
                            it.original_language,
                            it.popularity,
                            it.vote_count,
                            it.adult,
                            it.vote_average
                        )
                    )
                }
            }

            override fun onFailure(msg: String) {
                mutableLiveData.value = DetailMovieState(ViewState.FAILURE,msg,null)
            }

        })
        return mutableLiveData
    }

    override fun getTvById(id: String): MutableLiveData<DetailTvState> {
        val mutableLiveData = MutableLiveData<DetailTvState>()
        mutableLiveData.value = DetailTvState(ViewState.LOADING, "", null)
        remoteDataSource.getTvById(id, object : RemoteDataSource.LoadDetailTvCallback {
            override fun onSuccess(detailTvResponse: DetailTvResponse) {
                mutableLiveData.value = DetailTvState(ViewState.SUCCESS, "", detailTvResponse.let {
                    TvEntity(
                        it.poster_path,
                        it.backdrop_path,
                        it.overview,
                        it.first_air_date,
                        it.id,
                        it.original_name,
                        it.name,
                        it.original_language,
                        it.popularity,
                        it.vote_count,
                        it.vote_average
                    )
                })
            }

            override fun onFailure(msg: String) {
                mutableLiveData.value = DetailTvState(ViewState.FAILURE, msg, null)
            }

        })
        return mutableLiveData
    }
}