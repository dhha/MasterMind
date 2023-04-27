package com.example.mastermind.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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
}