package com.aditya.moviebajp.network

import com.aditya.moviebajp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


    const val BASE_URL_IMAGE = BuildConfig.BASE_URL_IMAGE

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor {
            var request = it.request()
            val url = request.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            request = request.newBuilder().url(url).build()
            return@Interceptor it.proceed(request)
        }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun restApi():RestApi = retrofit.create(RestApi::class.java)
}