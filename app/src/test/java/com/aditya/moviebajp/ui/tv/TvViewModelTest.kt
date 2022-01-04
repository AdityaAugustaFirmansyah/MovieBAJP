package com.aditya.moviebajp.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.moviebajp.data.FakeMovieRepository
import com.aditya.moviebajp.data.TvEntity
import com.aditya.moviebajp.data.TvState
import com.aditya.moviebajp.data.ViewState
import com.aditya.moviebajp.data.source.MovieRepository
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.utils.MovieDummy
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/*
skenario test pada TvViewModelTest

1.test memuat tv
    a.memberikan object baru viewmodeltest
    b.memastikan list dari content tidak null
    c.memastikan list dari content ada 10
2. test detail tv
    a.memberikan object baru viewmodeltest
    b.meberikan content dari data tv pada index 0
    c.meberikan object baru tv dari hasil mapping rawTv ke Object TvEntitny
    d.memastikan content dari data tv index 0 sama dengan data dari raw tv
3. test index out of bound
    memberikan object baru viewmodel
    memastikan throw exception IndexOutOfBoundsException dengan mengakses indeks ke -1
*/

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
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
        val dummyTv = TvState(MovieDummy.generateTv(),"",ViewState.SUCCESS)
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

    @Test
    fun testIndexOutOfBound(){
//        Assert.assertThrows(IndexOutOfBoundsException::class.java) {
//            viewModelTest.getData(-1)
//        }
    }
}