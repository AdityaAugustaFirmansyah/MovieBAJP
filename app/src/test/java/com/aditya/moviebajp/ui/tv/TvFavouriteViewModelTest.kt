package com.aditya.moviebajp.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.TvState
import com.aditya.moviebajp.vo.Status
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.utils.MovieDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvFavouriteViewModelTest {
    private lateinit var viewModelTest: TvViewModel

    @Mock
    private lateinit var repository: MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<TvState>

    @Before
    fun setUp(){
        viewModelTest= TvViewModel(repository)
    }

    @Test
    fun testLoadTv() {
        val dummyTv = TvState(MovieDummy.generateTv(),"", Status.SUCCESS)
        val tv = MutableLiveData<TvState>()
        tv.value = dummyTv
        `when`(repository.getAllTv()).thenReturn(tv)
        val tvEntity = viewModelTest.getData().value
        verify(repository).getAllTv()
        assertNotNull(tvEntity)
        assertEquals(dummyTv.tvs.size.toLong(),tvEntity?.tvs?.size?.toLong())
        viewModelTest.getData().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}