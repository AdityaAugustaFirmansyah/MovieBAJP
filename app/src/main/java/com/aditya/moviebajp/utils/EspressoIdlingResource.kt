package com.aditya.moviebajp.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idling = CountingIdlingResource(RESOURCE)
    fun increment(){
        idling.increment()
    }

    fun decrement(){
        idling.decrement()
    }
}