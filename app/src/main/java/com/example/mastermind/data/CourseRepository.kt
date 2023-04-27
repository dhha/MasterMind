package com.example.mastermind.data

import androidx.lifecycle.LiveData

class CourseRepository(private val courseDao: CourseDao) {

    val getAllCourses : LiveData<List<Course>> = courseDao.getAllCourses()

    suspend fun addCourse(course: Course){

        courseDao.addCourse(course)

    }
}