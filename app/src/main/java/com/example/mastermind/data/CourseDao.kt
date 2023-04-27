package com.example.mastermind.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCourse(course: Course)

    @Query("SELECT * FROM course_table ORDER BY startDate DESC")
    fun getAllCourses() : LiveData<List<Course>>
}