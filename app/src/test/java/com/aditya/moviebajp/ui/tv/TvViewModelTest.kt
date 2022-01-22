package com.aditya.moviebajp.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.utils.SortUtils
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
class TvViewModelTest {
    private lateinit var viewModelTest: TvViewModel

    @Mock
    private lateinit var repository: MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp(){
        viewModelTest= TvViewModel(repository)
    }

    @Test
    fun testLoadTv() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tv.value = dummyTv
        `when`(repository.getAllTv(SortUtils.DEFAULT)).thenReturn(tv)
        val tvEntity = viewModelTest.getData(SortUtils.DEFAULT).value
        verify(repository).getAllTv(SortUtils.DEFAULT)
        assertNotNull(tvEntity)
        assertEquals(dummyTv.data?.size?.toLong(),tvEntity?.data?.size?.toLong())
        viewModelTest.getData(SortUtils.DEFAULT).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}