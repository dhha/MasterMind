package com.example.mastermind.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mastermind.repository.CourseRepository
import com.example.mastermind.data.MastermindDatabase
import com.example.mastermind.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {
    private val getAllCourses : LiveData<List<Course>>
    private val courseRepository : CourseRepository

    init {

        val courseDao = MastermindDatabase.getDatabase(application).courseDao()

        courseRepository = CourseRepository(courseDao)

        getAllCourses = courseRepository.getAllCourses

    }

    fun addCourse(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.addCourse(course)
        }
    }

    fun getAllCourse(): LiveData<List<Course>> = getAllCourses
}