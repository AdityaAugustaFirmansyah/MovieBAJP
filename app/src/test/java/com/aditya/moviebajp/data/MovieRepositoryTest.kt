package com.aditya.moviebajp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.aditya.moviebajp.data.source.local.LocalDataSource
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity
import com.aditya.moviebajp.data.source.remote.RemoteDataSource
import com.aditya.moviebajp.utils.*
import com.aditya.moviebajp.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private var movieRepository = FakeMovieRepository(remote,local,appExecutors)

    private val tvDetailResponse = MovieDummy.generateDetailTvResponse()
    private val movieDetailResponse = MovieDummy.generateDetailMovieResponse()

    @Test
    fun getAllMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getAllMovie(SortUtils.getSortedQueryMovie(SortUtils.DEFAULT))).thenReturn(dataSourceFactory)
        movieRepository.getAllMovie(SortUtils.DEFAULT)
        val movieEntity = Resource.success(PagedListUtil.mockPageList(MovieDummy.generateMovies()))
        verify(local).getAllMovie(SortUtils.getSortedQueryMovie(SortUtils.DEFAULT))
        assertNotNull(movieEntity.data)
        assertEquals(movieEntity.data?.size?.toLong(),MovieDummy.generateMoviesResponse().results.size.toLong())
    }

    @Test
    fun getAllTv(){
        val dataSourceFactory= mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvEntity>
        `when`(local.getAllTv(SortUtils.getSortedQueryTv(""))).thenReturn(dataSourceFactory)
        movieRepository.getAllTv("")

        val tvEntity = Resource.success(PagedListUtil.mockPageList(MovieDummy.generateTv()))
        verify(local).getAllTv(SortUtils.getSortedQueryTv(SortUtils.DEFAULT))
        assertNotNull(tvEntity.data)
        assertEquals(tvEntity.data?.size?.toLong(),MovieDummy.generateTvResponse().results.size.toLong())
    }

    @Test
    fun getDetailMovie(){
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = MovieDummy.getDetailMovie()
        val movie = LiveDataTest.getValue(movieRepository.getMovieById(movieDetailResponse.id.toString()))
        `when`(local.getMovie(movieDetailResponse.id)).thenReturn(dummyMovie)
        verify(local).getMovie(movieDetailResponse.id)
        assertNotNull(movie)
        assertNotNull(movie.data)
        assertNotNull(movie.data?.title)
        assertEquals(movieDetailResponse.id,movie.data?.id)
    }

    @Test
    fun getDetailTv(){
        val dummyTv = MutableLiveData<TvEntity>()
        dummyTv.value = MovieDummy.getDetailTv()
        val tv = LiveDataTest.getValue(movieRepository.getTvById(tvDetailResponse.id.toString()))
        `when`(local.getTv(tvDetailResponse.id)).thenReturn(dummyTv)
        verify(local).getTv(tvDetailResponse.id)
        assertNotNull(tv)
        assertNotNull(tv.data)
        assertNotNull(tv.data?.name)
        assertEquals(tvDetailResponse.id,tv.data?.id)
    }
}