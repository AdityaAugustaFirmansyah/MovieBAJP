package com.aditya.moviebajp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aditya.moviebajp.data.source.remote.response.DetailMovieResponse
import com.aditya.moviebajp.data.source.remote.response.DetailTvResponse
import com.aditya.moviebajp.data.source.remote.response.MovieResponse
import com.aditya.moviebajp.data.source.remote.response.TvResponse
import com.aditya.moviebajp.data.source.remote.network.ApiClient
import com.aditya.moviebajp.data.source.remote.network.RestApi
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

    fun getAllMovie():LiveData<ApiResponse<MovieResponse>>{
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieResponse>>()
        ApiClient.restApi().getAllMovie().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    response.body()?.let { result.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    result.value= ApiResponse.error(response.code().toString(),null)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                result.value= t.message?.let { ApiResponse.error(it,null) }
                EspressoIdlingResource.decrement()
            }

        })
        return result
    }
    fun getAllTv():LiveData<ApiResponse<TvResponse>>{
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvResponse>>()
        restApi.getAllTv().enqueue(object : Callback<TvResponse>{
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful){
                    response.body()?.let { result.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    result.value = ApiResponse.error(response.message(),null)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                result.value = t.message?.let { ApiResponse.error(it,null) }
                EspressoIdlingResource.decrement()
            }

        })
        return result
    }
    fun getMovieById(id: String):LiveData<ApiResponse<DetailMovieResponse>>{
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        restApi.getMovieById(id).enqueue(object : Callback<DetailMovieResponse>{
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { result.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    result.value = ApiResponse.error(response.message(),null)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                result.value = t.message?.let { ApiResponse.error(it,null) }
            }

        })
        return result
    }

    fun getTvById(id: String):LiveData<ApiResponse<DetailTvResponse>>{
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<DetailTvResponse>>()
        restApi.getTvById(id).enqueue(object : Callback<DetailTvResponse>{
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { result.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }else{
                    result.value = ApiResponse.error(response.message(),null)
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                result.value = ApiResponse.error("${t.message}",null)
                EspressoIdlingResource.decrement()
            }

        })
        return result
    }

}