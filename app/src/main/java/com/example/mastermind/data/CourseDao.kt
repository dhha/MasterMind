package com.example.mastermind.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mastermind.model.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCourse(course: Course)

    @Query("SELECT * FROM course_table ORDER BY courseName ASC")
    fun getAllCourses() : LiveData<List<Course>>

    @Query("SELECT * FROM course_table WHERE code = :courseCode")
    fun getCourse(courseCode : String) : LiveData<Course>
}