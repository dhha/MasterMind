package com.example.mastermind.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class, Course::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MastermindDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun courseDao() : CourseDao

    companion object{

        @Volatile
        private var INSTANCE : MastermindDatabase? = null

        fun getDatabase(context : Context) : MastermindDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                MastermindDatabase::class.java,
                "mastermind_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}