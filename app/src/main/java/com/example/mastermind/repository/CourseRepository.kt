package com.example.mastermind.repository

import androidx.lifecycle.LiveData
import com.example.mastermind.data.CourseDao
import com.example.mastermind.model.Course

class CourseRepository(private val courseDao: CourseDao) {

    val getAllCourses : LiveData<List<Course>> = courseDao.getAllCourses()

    suspend fun addCourse(course: Course){

        courseDao.addCourse(course)

    }

    suspend fun getCourse(courseCode : String) = courseDao.getCourse(courseCode)
}