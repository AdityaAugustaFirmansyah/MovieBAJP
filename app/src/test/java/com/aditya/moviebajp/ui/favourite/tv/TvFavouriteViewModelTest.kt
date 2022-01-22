package com.aditya.moviebajp.ui.favourite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity
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
class TvFavouriteViewModelTest {

    private lateinit var viewModelTest: TvFavouriteViewModel

    @Mock
    private lateinit var repository: MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<TvEntity>>

    @Before
    fun setUp(){
        viewModelTest= TvFavouriteViewModel(repository)
    }

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Test
    fun testLoadTv() {
        val dummyTv = pagedList
        val tv = MutableLiveData<PagedList<TvEntity>>()
        tv.value = dummyTv
        `when`(repository.getAllTvFavourite()).thenReturn(tv)
        val tvEntity = viewModelTest.getData().value
        verify(repository).getAllTvFavourite()
        TestCase.assertNotNull(tvEntity)
        TestCase.assertEquals(dummyTv.size.toLong(), tvEntity?.size?.toLong())
        viewModelTest.getData().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}