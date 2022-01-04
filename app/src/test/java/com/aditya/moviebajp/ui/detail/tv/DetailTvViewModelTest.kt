package com.aditya.moviebajp.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.DetailTvState
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.utils.MovieDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
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
class DetailTvViewModelTest{
    private lateinit var viewModel:DetailTvViewModel
    @Mock
    private lateinit var repository:MovieRepository
    @Mock
    private lateinit var observer: Observer<DetailTvState>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val tvId = MovieDummy.generateTv()[0].id
    private val dummyDetail = DetailTvState(ViewState.SUCCESS,"",MovieDummy.getDetailTv(0))

    @Before
    fun setUp(){
        viewModel = DetailTvViewModel(tvId,repository)
    }

    @Test
    fun getDetail(){
        val detailTv = MutableLiveData<DetailTvState>()
        detailTv.value = dummyDetail
        `when`(repository.getTvById(tvId.toString())).thenReturn(detailTv)
        val detailEntity = viewModel.detailTvLiveData().value
        verify(repository).getTvById(tvId.toString())
        assertNotNull(detailEntity)
        assertEquals(dummyDetail.message, detailEntity?.message)
        assertEquals(dummyDetail.viewState, detailEntity?.viewState)
        assertEquals(dummyDetail.response, detailEntity?.response)

        viewModel.detailTvLiveData().observeForever(observer)
        verify(observer).onChanged(dummyDetail)
    }
}