package com.aditya.moviebajp.data

import androidx.lifecycle.MutableLiveData
import com.aditya.moviebajp.data.source.MovieDataSource
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import com.aditya.moviebajp.data.source.remote.response.DetailTvResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse
import com.aditya.moviebajp.vo.Status

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {
    override fun getAllMovie(): MutableLiveData<MovieState> {
        val mutableLiveData = MutableLiveData<MovieState>()
        mutableLiveData.value = MovieState(Status.LOADING, mutableListOf(), "")
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onSuccess(movieResponse: MovieResponse) {
                mutableLiveData.value = MovieState(
                    Status.SUCCESS,
                    movieResponse.results.map {
                        MovieEntity(
                            it.posterPath,
                            it.backdropPath,
                            it.overview,
                            it.releaseDate,
                            it.id,
                            it.originalTitle,
                            it.title,
                            it.originalLanguage,
                            it.popularity,
                            it.voteCount,
                            it.adult,
                            it.voteAverage
                        )
                    }, ""
                )
            }

            override fun onFailure(msg:String) {
                mutableLiveData.value = MovieState(Status.FAILURE, mutableListOf(), msg)
            }
        })
        return mutableLiveData
    }

    override fun getAllTv(): MutableLiveData<TvState> {
        val mutableLiveData = MutableLiveData<TvState>()
        mutableLiveData.value = TvState(mutableListOf(), "", Status.LOADING)
        remoteDataSource.getAllTv(object : RemoteDataSource.LoadTvCallback{
            override fun onSuccess(tvResponse: TvResponse) {
                mutableLiveData.value = tvResponse.results.map {
                    TvEntity(
                        it.posterPath,
                        it.backdropPath,
                        it.overview,
                        it.firstAirDate,
                        it.id,
                        it.originalName,
                        it.name,
                        it.originalLanguage,
                        it.popularity,
                        it.voteCount,
                        it.voteAverage
                    )
                }.let { TvState(it, "", Status.SUCCESS) }
            }

            override fun onFailure(msg: String) {
                mutableLiveData.value = TvState(mutableListOf(), msg, Status.FAILURE)
            }

        })
        return mutableLiveData
    }

    override fun getMovieById(id: String): MutableLiveData<DetailMovieState> {
        val mutableLiveData = MutableLiveData<DetailMovieState>()
        mutableLiveData.value = DetailMovieState(Status.LOADING, "", null)
        remoteDataSource.getMovieById(id, object : RemoteDataSource.LoadDetailMovieCallback{
            override fun onSuccess(detailMovieResponse: DetailMovieResponse) {
                detailMovieResponse.let {
                    mutableLiveData.value = DetailMovieState(
                        Status.SUCCESS, "", MovieEntity(
                            it.posterPath,
                            it.backdropPath,
                            it.overview,
                            it.releaseDate,
                            it.id,
                            it.originalTitle,
                            it.title,
                            it.originalLanguage,
                            it.popularity,
                            it.voteCount,
                            it.adult,
                            it.voteAverage
                        )
                    )
                }
            }

            override fun onFailure(msg: String) {
                mutableLiveData.value = DetailMovieState(Status.FAILURE,msg,null)
            }

        })
        return mutableLiveData
    }

    override fun getTvById(id: String): MutableLiveData<DetailTvState> {
        val mutableLiveData = MutableLiveData<DetailTvState>()
        mutableLiveData.value = DetailTvState(Status.LOADING, "", null)
        remoteDataSource.getTvById(id, object : RemoteDataSource.LoadDetailTvCallback {
            override fun onSuccess(detailTvResponse: DetailTvResponse) {
                mutableLiveData.value = DetailTvState(Status.SUCCESS, "", detailTvResponse.let {
                    TvEntity(
                        it.posterPath,
                        it.backdropPath,
                        it.overview,
                        it.firstAirDate,
                        it.id,
                        it.originalName,
                        it.name,
                        it.originalLanguage,
                        it.popularity,
                        it.voteCount,
                        it.voteAverage
                    )
                })
            }

            override fun onFailure(msg: String) {
                mutableLiveData.value = DetailTvState(Status.FAILURE, msg, null)
            }

        })
        return mutableLiveData
    }
}