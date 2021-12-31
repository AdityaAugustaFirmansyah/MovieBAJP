package com.aditya.moviebajp.ui.movie

import com.aditya.moviebajp.data.MovieEntity
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test


/*
skenario test pada MovieViewModelTest

1.test memuat movie
    a.memberikan object baru viewmodel
    b.memastikan list dari content tidak null
    c.memastikan list dari content ada 10
2. test detail movie
    a.memberikan object baru viewmodel
    b.meberikan content dari data movie pada index 0
    c.meberikan object baru movie dari hasil mapping rawMovie ke Object MovieEntitny
    d.memastikan content dari data movie index 0 sama dengan data dari raw movie
3. test index out of bound
    memberikan object baru viewmodel
    memastikan throw exception IndexOutOfBoundsException dengan mengakses indeks ke -1
*/




class MovieViewModelTest {

    private lateinit var  viewModel:MovieViewModel
    private val rawMovie = "{\n" +
            "  \"poster_path\": \"/xFw9RXKZDvevAGocgBK0zteto4U.jpg\",\n" +
            "  \"adult\": false,\n" +
            "  \"overview\": \"From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.\",\n" +
            "  \"release_date\": \"2016-08-03\",\n" +
            "  \"genre_ids\": [\n" +
            "    14,\n" +
            "    28,\n" +
            "    80\n" +
            "  ],\n" +
            "  \"genre_name\": [\"fantasy\",\"Action\",\"Crime\"],\n" +
            "  \"id\": 297761,\n" +
            "  \"original_title\": \"Suicide Squad\",\n" +
            "  \"original_language\": \"en\",\n" +
            "  \"title\": \"Suicide Squad\",\n" +
            "  \"backdrop_path\": \"/sMRwI5trKI6qhxYcjPgGghmPBef.jpg\",\n" +
            "  \"popularity\": 48.261451,\n" +
            "  \"vote_count\": 1466,\n" +
            "  \"video\": false,\n" +
            "  \"vote_average\": 5.91\n" +
            "}"

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun testLoadMovie() {
        assertNotNull(viewModel.getData())
        assertEquals(10,viewModel.getData().size)
    }

    @Test
    fun testDetailMovie(){
        val movies = viewModel.getData(0)
        val movie =  Gson().fromJson(rawMovie,MovieEntity::class.java)
        assertEquals(movie.poster_path,movies.poster_path)
        assertEquals(movie.adult,movies.adult)
        assertEquals(movie.overview,movies.overview)
        assertEquals(movie.release_date,movies.release_date)
        assertEquals(movie.genre_name,movies.genre_name)
        assertEquals(movie.id,movies.id)
        assertEquals(movie.original_title,movies.original_title)
        assertEquals(movie.original_language,movies.original_language)
        assertEquals(movie.title,movies.title)
        assertEquals(movie.backdrop_path,movies.backdrop_path)
        assertEquals(movie.popularity,movies.popularity)
        assertEquals(movie.vote_count,movies.vote_count)
        assertEquals(movie.vote_average,movies.vote_average)
    }

    @Test
    fun testIndexOutOfBound(){
        assertThrows(IndexOutOfBoundsException::class.java) {
            viewModel.getData(-1)
        }
    }
}