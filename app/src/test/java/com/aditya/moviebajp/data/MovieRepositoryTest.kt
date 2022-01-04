package com.aditya.moviebajp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.utils.LiveDataTest
import com.aditya.moviebajp.utils.MovieDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private var movieRepository = FakeMovieRepository(remote)

    private val movieResponse = MovieDummy.generateMoviesResponse()
    private val tvResponse = MovieDummy.generateTvResponse()
    private val tvDetailResponse = MovieDummy.generateDetailTvResponse()
    private val movieDetailResponse = MovieDummy.generateDetailMovieResponse()

    @Test
    fun getAllMovies(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onSuccess(movieResponse)
            null
        }.`when`(remote).getAllMovie(any())
        val movies = LiveDataTest.getValue(movieRepository.getAllMovie())
        verify(remote).getAllMovie(any())
        assertNotNull(movies)
        assertEquals(movieResponse.results.size.toLong(),movies.success.size.toLong())
    }

    @Test
    fun getAllTv(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onSuccess(tvResponse)
            null
        }.`when`(remote).getAllTv(any())
        val tvs = LiveDataTest.getValue(movieRepository.getAllTv())
        verify(remote).getAllTv(any())
        assertNotNull(tvs)
        assertEquals(tvResponse.results.size.toLong(),tvs.tvs.size.toLong())
    }

    @Test
    fun getDetailMovie(){
        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadDetailMovieCallback)
                .onSuccess(movieDetailResponse)
            null
        }.`when`(remote).getMovieById(eq(movieDetailResponse.id.toString()), any())
        val movie = LiveDataTest.getValue(movieRepository.getMovieById(movieDetailResponse.id.toString()))
        verify(remote).getMovieById(eq(movieDetailResponse.id.toString()),any())
        assertNotNull(movie)
        assertEquals(movieDetailResponse.id,movie.response?.id)
    }

    @Test
    fun getDetailTv(){
        doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadDetailTvCallback)
                .onSuccess(tvDetailResponse)
            null
        }.`when`(remote).getTvById(eq(tvDetailResponse.id.toString()), any())
        val tv = LiveDataTest.getValue(movieRepository.getTvById(tvDetailResponse.id.toString()))
        assertNotNull(tv)
        assertEquals(tvDetailResponse.id,tv.response?.id)
    }
}