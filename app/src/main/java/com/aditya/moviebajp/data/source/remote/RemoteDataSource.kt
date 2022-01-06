package com.aditya.moviebajp.data.source.remote

import com.aditya.moviebajp.data.source.remote.response.*
import com.aditya.moviebajp.network.ApiClient
import com.aditya.moviebajp.network.RestApi
import com.aditya.moviebajp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val restApi: RestApi) {
    companion object{
        @Volatile
        private var instance:RemoteDataSource?=null
        fun getInstance(restApi: RestApi):RemoteDataSource = instance?: synchronized(this){
            instance?:RemoteDataSource(restApi)
        }
    }

    fun getAllMovie(callback: LoadMovieCallback){
        EspressoIdlingResource.increment()
        ApiClient.restApi().getAllMovie().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    response.body()?.let { callback.onSuccess(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    callback.onFailure("${response.code()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onFailure("${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
    }
    fun getAllTv(callback:LoadTvCallback){
        EspressoIdlingResource.increment()
        restApi.getAllTv().enqueue(object : Callback<TvResponse>{
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful){
                    response.body()?.let { callback.onSuccess(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    callback.onFailure("${response.code()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                callback.onFailure("${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
    }
    fun getMovieById(id: String, callback: LoadDetailMovieCallback){
        EspressoIdlingResource.increment()
        restApi.getMovieById(id).enqueue(object : Callback<DetailMovieResponse>{
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { callback.onSuccess(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    callback.onFailure("${response.code()}")
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                callback.onFailure("${t.message}")
            }

        })
    }
    fun getTvById(id: String, callback: LoadDetailTvCallback){
        EspressoIdlingResource.increment()
        restApi.getTvById(id).enqueue(object : Callback<DetailTvResponse>{
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { callback.onSuccess(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    callback.onFailure("${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                callback.onFailure("${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
    }

    interface LoadMovieCallback{
        fun onSuccess(movieResponse: MovieResponse)
        fun onFailure(msg:String)
    }

    interface LoadTvCallback{
        fun onSuccess(tvResponse: TvResponse)
        fun onFailure(msg:String)
    }

    interface LoadDetailMovieCallback{
        fun onSuccess(detailMovieResponse: DetailMovieResponse)
        fun onFailure(msg:String)
    }

    interface LoadDetailTvCallback{
        fun onSuccess(detailTvResponse: DetailTvResponse)
        fun onFailure(msg:String)
    }
}