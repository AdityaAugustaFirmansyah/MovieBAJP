package com.aditya.moviebajp.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.moviebajp.data.source.local.entity.MovieEntity
import com.aditya.moviebajp.data.source.local.entity.TvEntity

@Database(entities = [MovieEntity::class,TvEntity::class], version = 3, exportSchema = false)
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseLocal? = null
        fun getInstance(context: Context): DatabaseLocal =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext,
                    DatabaseLocal::class.java,
                    "movie.db")
                    .fallbackToDestructiveMigration()
                    .build().apply {
                        INSTANCE = this
                    }
            }
    }
}