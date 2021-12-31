package com.aditya.moviebajp

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class NestedScrollToAction
    (private val original:ViewAction = scrollTo())
    : ViewAction by original{
    override fun getConstraints(): Matcher<View> = allOf(
        withEffectiveVisibility(Visibility.VISIBLE),
        isDescendantOfA(anyOf(
            isAssignableFrom(NestedScrollView::class.java),
            isAssignableFrom(ScrollView::class.java),
            isAssignableFrom(HorizontalScrollView::class.java),
            isAssignableFrom(ListView::class.java))))
}