package com.aditya.moviebajp.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor,
    private val mainThread: Executor
){

    fun diskIO():Executor = diskIO
    fun mainThread():Executor = mainThread

    constructor():this(
        Executors.newSingleThreadExecutor(),
        MainThreadExecutors()
    )

    class MainThreadExecutors : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(p0: Runnable) {
            mainThreadHandler.post(p0)
        }
    }
}