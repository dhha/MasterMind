package com.example.mastermind.ui.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mastermind.ui.Grade.Grade
import com.example.mastermind.ui.Grade.GradeDao

@Database(
    entities = [Grade::class, Attachment::class, Course::class, Schedule::class, Notes::class],
    version = 5
)
abstract class MasterMindDatabase: RoomDatabase(){
    abstract fun getGradeDao(): GradeDao
    abstract fun getCourseDao(): CourseDao
    abstract fun getScheduleDao(): ScheduleDao

    abstract fun getNoteDao(): NoteDao

    companion object{
        @Volatile
        private var instance: MasterMindDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // Create a instance also return the instance
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context)= Room.databaseBuilder(context.applicationContext,
            MasterMindDatabase::class.java, "MasterMindDB").fallbackToDestructiveMigration().build()
    }
}