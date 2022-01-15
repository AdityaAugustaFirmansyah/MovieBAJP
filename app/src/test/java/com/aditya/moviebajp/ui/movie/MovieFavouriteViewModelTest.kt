package com.aditya.moviebajp.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.MovieState
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




@RunWith(MockitoJUnitRunner::class)
class MovieFavouriteViewModelTest {
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
}