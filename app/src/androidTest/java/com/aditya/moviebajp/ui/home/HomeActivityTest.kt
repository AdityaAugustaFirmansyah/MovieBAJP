package com.aditya.moviebajp.ui.home

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.aditya.moviebajp.NestedScrollToAction
import com.aditya.moviebajp.R
import com.aditya.moviebajp.utils.MovieDummy
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test


/*
* 1. pengujian menampilkan list movie
*   - Aplikasi terbuka dan menampilkan tab layout,viewpager dan, recyclerview dari data movie
*   - memastikan recyclerview telah di tampilkan
*   - Memberi Tindakan Scroll recyclerview dari posisi paling atas hingga ke bawah
*
* 2. pengujian menampilkan list tv
*   - Aplikasi terbuka dan menampilkan tab layout,viewpager dan, recyclerview dari data movie
*   - Memberi Tindakan click pada bagian tab yang memiliki teks "Tv"
*   - memastikan rvTv telah di tampilkan
*   - Memberi Tindakan Scroll rvTv dari posisi paling atas hingga ke bawah
*
* 3. pengujian menampilkan detail movie
*   - Aplikasi terbuka dan menampilkan tab layout,viewpager dan, recycler view dari data movie
*   - memastikan recyclerview telah di tampilkan
*   - memberi tindakan click pada recyclerview untuk posisi pertama
*   - memastikan tv_title_detail sudah di tampilkan
*   - memastikan tv_title_detail menampilkan hasil sesuai expektasi
*   - memastikan poster_detail sudah di tampilkan
*   - memastikan backdrop sudah di tampilkan
*   - memastikan tv_language_detail sudah di tampilkan
*   - memastikan tv_language_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_date_detail sudah di tampilkan
*   - memastikan tv_date_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_adult_detail sudah di tampilkan
*   - memastikan tv_adult_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_popularity_detail sudah di tampilkan
*   - memastikan tv_popularity_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_total_detail sudah di tampilkan
*   - memastikan tv_total_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_avarage_detail sudah di tampilkan
*   - memastikan tv_avarage_detail menampilkan hasil sesuai expektasi
*   - memberikan aksi pada id textView18 untuk scroll
*   - memastikan tv_description_detail sudah di tampilkan
*   - memastikan tv_description_detail menampilkan hasil sesuai expektasi
*
* 4. pengujian menampilkan detail tv
*   - Aplikasi terbuka dan menampilkan tab layout,viewpager dan, recycler view dari data movie
*   - Memberi Tindakan click pada bagian tab yang memiliki teks "Tv"
*   - memastikan rvTv telah di tampilkan
*   - memberi tindakan click pada recycler view untuk posisi pertama
*   - memastikan tv_title_detail sudah di tampilkan
*   - memastikan tv_title_detail menampilkan hasil sesuai expektasi
*   - memastikan poster_detail sudah di tampilkan
*   - memastikan backdrop sudah di tampilkan
*   - memastikan tv_language_detail sudah di tampilkan
*   - memastikan tv_language_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_date_detail sudah di tampilkan
*   - memastikan tv_date_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_popularity_detail sudah di tampilkan
*   - memastikan tv_popularity_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_total_detail sudah di tampilkan
*   - memastikan tv_total_detail menampilkan hasil sesuai expektasi
*   - memastikan tv_avarage_detail sudah di tampilkan
*   - memastikan tv_avarage_detail menampilkan hasil sesuai expektasi
*   - memberikan aksi pada id textView18 untuk scroll
*   - memastikan tv_description_detail sudah di tampilkan
*   - memastikan tv_description_detail menampilkan hasil sesuai expektasi
*
* 5. pengujian untuk menampilkan data kosong pada movie
*   - Aplikasi terbuka dan menampilkan tab layout,viewpager dan, recycler view dari data movie
*   - mengecek data yang ditampikan kosong atau tidak
*   - jika kosong maka diberi aksi recycler view gone dan view empty visible
*   - jika ada data maka diberi aksi recycler view visible dan view empty gone
*
* 6. pengujian untuk menampilkan data kosong pada tv
*   - Aplikasi terbuka dan menampilkan tab layout,viewpager dan, recycler view dari data movie
*   - Memberi Tindakan click pada bagian tab yang memiliki teks "Tv"
*   - mengecek data yang ditampikan kosong atau tidak
*   - jika kosong maka diberi aksi recycler view gone dan view empty visible
*   - jika ada data maka diberi aksi recycler view visible dan view empty gone
*/

class HomeActivityTest {

    private val resources: Resources =
        ApplicationProvider.getApplicationContext<Context>().resources
    private val dummyMovie = MovieDummy.generateMovies()
    private val dummyTv = MovieDummy.generateTv()

    @Before
    fun launchActivity() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadDataMovie() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText("Suicide Squad")))
        onView(withId(R.id.poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language_detail)).check(matches(withText("en")))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(withText("2016-08-03")))
        onView(withId(R.id.tv_adult_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_adult_detail)).check(matches(withText("no")))
        onView(withId(R.id.tv_popularity_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_detail)).check(matches(withText("48.261451")))
        onView(withId(R.id.tv_total_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_total_detail)).check(matches(withText("1466")))
        onView(withId(R.id.tv_avarage_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_avarage_detail)).check(matches(withText("5.91")))
        onView(withId(R.id.textView18)).perform(betterScrollTo())
        onView(withId(R.id.tv_description_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_detail)).check(matches(withText("From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.")))
    }

    @Test
    fun loadDataTv() {
        onView(withText(resources.getString(R.string.tv))).perform(click())
        onView(withId(R.id.rvTv)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTv.size
            )
        )
    }

    @Test
    fun emptyDataMovie() {
        onView(withId(R.id.empty_movie)).perform(setVisibility(VISIBLE))
        onView(withId(R.id.recyclerView)).perform(setVisibility(GONE))
        onView(withId(R.id.empty_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun emptyDataTv() {
        onView(withText(resources.getString(R.string.tv))).perform(click())
        onView(withId(R.id.empty_tv)).perform(setVisibility(VISIBLE))
        onView(withId(R.id.rvTv)).perform(setVisibility(GONE))
        onView(withId(R.id.empty_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTv)).check(matches(not(isDisplayed())))
    }

    @Test
    fun loadDetailTv() {
        onView(withText(resources.getString(R.string.tv))).perform(click())
        onView(withId(R.id.rvTv)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )

        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText("Pretty Little Liars")))
        onView(withId(R.id.poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language_detail)).check(matches(withText("en")))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(withText("2010-06-08")))
        onView(withId(R.id.tv_popularity_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_detail)).check(matches(withText("47.432451")))
        onView(withId(R.id.tv_total_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_total_detail)).check(matches(withText("133")))
        onView(withId(R.id.tv_avarage_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_avarage_detail)).check(matches(withText("5.04")))
        onView(withId(R.id.textView18)).perform(betterScrollTo())
        onView(withId(R.id.tv_description_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_detail)).check(matches(withText("Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \"A\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.")))
    }

    private fun betterScrollTo(): ViewAction {
        return actionWithAssertions(NestedScrollToAction())
    }

    private fun setVisibility(visibility: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isAssignableFrom(View::class.java)

            override fun getDescription(): String = "Show / Hide View"

            override fun perform(uiController: UiController?, view: View?) {
                view?.visibility = visibility
            }

        }
    }
}