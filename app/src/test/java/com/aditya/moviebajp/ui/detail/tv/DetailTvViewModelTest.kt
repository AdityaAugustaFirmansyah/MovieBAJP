package com.aditya.moviebajp.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.utils.MovieDummy
import com.aditya.moviebajp.vo.Resource
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
class DetailTvViewModelTest{
    private lateinit var viewModel:DetailTvViewModel
    @Mock
    private lateinit var repository:MovieRepository
    @Mock
    private lateinit var observer: Observer<Resource<TvEntity>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val tvId = MovieDummy.generateTv()[0].id

    @Before
    fun setUp(){
        viewModel = DetailTvViewModel(tvId,repository)
    }

    @Test
    fun getDetail(){
        val dummyTv = Resource.success(MovieDummy.generateTv()[0])
        val detailTv = MutableLiveData<Resource<TvEntity>>()
        detailTv.value = dummyTv
        `when`(repository.getTvById(tvId.toString())).thenReturn(detailTv)
        val detailEntity = viewModel.detailTvLiveData().value
        verify(repository).getTvById(tvId.toString())
        assertNotNull(detailEntity)
        assertEquals(dummyTv.message, detailEntity?.message)
        assertEquals(dummyTv.status, detailEntity?.status)
        assertEquals(dummyTv.data, detailEntity?.data)

        viewModel.detailTvLiveData().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}