package com.example.mastermind.ui.model

import androidx.room.*

@Dao
interface CourseDao {
    @Query("Select * from Courses")
    suspend fun getAllCourses(): List<Course>
    @Insert
    suspend fun addCourse(course: Course)
    @Delete
    suspend fun deleteCourse(course: Course)
    @Update
    suspend fun updateCourse(course: Course)
}