package com.aditya.moviebajp.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor {
            var request = it.request()
            val url = request.url().newBuilder()
                .addQueryParameter("api_key", "cbbbb3394c404470037b89e9cc2ba6bb")
                .build()
            request = request.newBuilder().url(url).build()
            return@Interceptor it.proceed(request)
        }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}