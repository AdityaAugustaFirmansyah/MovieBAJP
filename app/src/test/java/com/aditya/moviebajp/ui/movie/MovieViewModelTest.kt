package com.aditya.moviebajp.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.MovieState
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.utils.MovieDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


/*
skenario test pada MovieViewModelTest

1.test memuat movie
    a.memberikan object baru viewmodel
    b.memastikan list dari content tidak null
    c.memastikan list dari content ada 10
2. test detail movie
    a.memberikan object baru viewmodel
    b.meberikan content dari data movie pada index 0
    c.meberikan object baru movie dari hasil mapping rawMovie ke Object MovieEntitny
    d.memastikan content dari data movie index 0 sama dengan data dari raw movie
3. test index out of bound
    memberikan object baru viewmodel
    memastikan throw exception IndexOutOfBoundsException dengan mengakses indeks ke -1
*/



@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel:MovieViewModel

    @Mock
    private lateinit var repository:MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MovieState>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun testLoadMovie() {
        val dummyMovie = MovieDummy.generateMoviesState()
        val movies = MutableLiveData<MovieState>()
        movies.value = dummyMovie
        `when`(repository.getAllMovie()).thenReturn(movies)
        val movieEntity = viewModel.getData().value
        verify(repository).getAllMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.success.size.toLong(),movieEntity?.success?.size?.toLong())

        viewModel.getData().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun testDetailMovie(){
//        val movies = viewModel.getData(0)
//        val movie =  Gson().fromJson(rawMovie,MovieEntity::class.java)
//        assertEquals(movie.poster_path,movies.poster_path)
//        assertEquals(movie.adult,movies.adult)
//        assertEquals(movie.overview,movies.overview)
//        assertEquals(movie.release_date,movies.release_date)
//        assertEquals(movie.genre_name,movies.genre_name)
//        assertEquals(movie.id,movies.id)
//        assertEquals(movie.original_title,movies.original_title)
//        assertEquals(movie.original_language,movies.original_language)
//        assertEquals(movie.title,movies.title)
//        assertEquals(movie.backdrop_path,movies.backdrop_path)
//        assertEquals(movie.popularity,movies.popularity)
//        assertEquals(movie.vote_count,movies.vote_count)
//        assertEquals(movie.vote_average,movies.vote_average)
    }

    @Test
    fun testIndexOutOfBound(){
//        assertThrows(IndexOutOfBoundsException::class.java) {
//            viewModel.getData(-1)
//        }
    }
}