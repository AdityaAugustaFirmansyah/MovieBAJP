package com.aditya.moviebajp.ui.tv

import com.aditya.moviebajp.data.TvEntity
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test

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

class TvViewModelTest {
    private lateinit var viewModelTest: TvViewModel
    private val rawTv = "{\n" +
            "    \"poster_path\": \"/aUPbHiLS3hCHKjtLsncFa9g0viV.jpg\",\n" +
            "    \"popularity\": 47.432451,\n" +
            "    \"id\": 31917,\n" +
            "    \"backdrop_path\": \"/ypLoTftyF5EpGBxJas4PThIdiU4.jpg\",\n" +
            "    \"vote_average\": 5.04,\n" +
            "    \"overview\": \"Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \\\"A\\\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.\",\n" +
            "    \"first_air_date\": \"2010-06-08\",\n" +
            "    \"origin_country\": [\n" +
            "      \"US\"\n" +
            "    ],\n" +
            "    \"genre_ids\": [\n" +
            "      18,\n" +
            "      9648\n" +
            "    ],\n" +
            "    \"genre_name\": [\n" +
            "      \"Drama\",\n" +
            "      \"Mystery\"\n" +
            "    ],\n" +
            "    \"original_language\": \"en\",\n" +
            "    \"vote_count\": 133,\n" +
            "    \"name\": \"Pretty Little Liars\",\n" +
            "    \"original_name\": \"Pretty Little Liars\"\n" +
            "  }"

    @Before
    fun setUp(){
        viewModelTest= TvViewModel()
    }

    @Test
    fun testLoadTv() {
        assertNotNull(viewModelTest.getData())
        assertEquals(10, viewModelTest.getData().size)
    }

    @Test
    fun testDetailTv(){
        val tvs = viewModelTest.getData(0)
        val tv = Gson().fromJson(rawTv,TvEntity::class.java)
        assertEquals(tv.id, tvs.id)
        assertEquals(tv.name, tvs.name)
        assertEquals(tv.poster_path, tvs.poster_path)
        assertEquals(tv.popularity, tvs.popularity)
        assertEquals(tv.backdrop_path, tvs.backdrop_path)
        assertEquals(tv.vote_average, tvs.vote_average)
        assertEquals(tv.overview, tvs.overview)
        assertEquals(tv.first_air_date, tvs.first_air_date)
        assertEquals(tv.original_language, tvs.original_language)
        assertEquals(tv.vote_count, tvs.vote_count)
        assertEquals(tv.original_name, tvs.original_name)
    }

    @Test
    fun testIndexOutOfBound(){
        Assert.assertThrows(IndexOutOfBoundsException::class.java) {
            viewModelTest.getData(-1)
        }
    }
}