package com.aditya.moviebajp.ui.favourite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.utils.MovieDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavouriteViewModelTest{
    private lateinit var viewModel: MovieFavouriteViewModel

    @Mock
    private lateinit var repository: MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>


    @Before
    fun setUp(){
        viewModel = MovieFavouriteViewModel(repository)
    }

    @Test
    fun testLoadMovie() {
        val dummyMovie = MovieDummy.generateMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovie
        `when`(repository.getAllMovieFavourite()).thenReturn(movies)
        val movieEntity = viewModel.getData().value
        verify(repository).getAllMovieFavourite()
        TestCase.assertNotNull(movieEntity)
        TestCase.assertEquals(dummyMovie.size.toLong(), movieEntity?.size?.toLong())

        viewModel.getData().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}