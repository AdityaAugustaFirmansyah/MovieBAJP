package com.aditya.moviebajp.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.DetailMovieState
import com.aditya.moviebajp.vo.Status
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.utils.MovieDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieFavouriteViewModelTest{
    private lateinit var viewModel: DetailMovieViewModel
    @Mock
    private lateinit var repository: MovieRepository
    @Mock
    private lateinit var observer: Observer<DetailMovieState>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val id = MovieDummy.getDetailMovie().id
    private val dummyDetail = DetailMovieState(Status.SUCCESS,"", MovieDummy.getDetailMovie())

    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel(id,repository)
    }

    @Test
    fun detail(){
        val detailMovie = MutableLiveData<DetailMovieState>()
        detailMovie.value = dummyDetail
        Mockito.`when`(repository.getMovieById(id.toString())).thenReturn(detailMovie)
        val detailEntity = viewModel.detailMovieLiveData().value
        verify(repository).getMovieById(id.toString())
        TestCase.assertNotNull(detailEntity)
        TestCase.assertEquals(dummyDetail.message, detailEntity?.message)
        TestCase.assertEquals(dummyDetail.status, detailEntity?.status)
        TestCase.assertEquals(dummyDetail.response, detailEntity?.response)

        viewModel.detailMovieLiveData().observeForever(observer)
        verify(observer).onChanged(dummyDetail)
    }
}