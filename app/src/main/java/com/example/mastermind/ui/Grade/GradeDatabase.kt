package com.example.mastermind.ui.Grade

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Grade::class], version = 1)
abstract class GradeDatabase : RoomDatabase() {
    abstract fun getGradeDao(): GradeDao

    companion object {
        @Volatile
        private var instance: GradeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // Create a instance also return the instance
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GradeDatabase::class.java, "gradedatabase"
        ).build()
    }
}