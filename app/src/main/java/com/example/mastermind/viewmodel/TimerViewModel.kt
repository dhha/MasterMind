package com.example.mastermind.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mastermind.data.MastermindDatabase
import com.example.mastermind.model.Course
import com.example.mastermind.model.Timer
import com.example.mastermind.repository.TimerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimerViewModel(application: Application) : AndroidViewModel(application) {
    private val timerRepository : TimerRepository

    init {

        val timerDao = MastermindDatabase.getDatabase(application).timerDao()

        timerRepository = TimerRepository(timerDao)


    }

    fun addTimer(timer : Timer){
        viewModelScope.launch(Dispatchers.IO) {
            timerRepository.addTimer(timer)
        }
    }

    fun getCourseTimers(courseCode : String) = timerRepository.getCourseTimers(courseCode)
}