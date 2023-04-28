package com.example.mastermind.repository

import androidx.lifecycle.LiveData
import com.example.mastermind.data.TimerDao
import com.example.mastermind.model.Timer

class TimerRepository(private val timerDao : TimerDao) {

    suspend fun addTimer(timer : Timer){

        timerDao.addTimer(timer)

    }

    fun getCourseTimers(courseCode : String) = timerDao.getCourseTimers(courseCode)

}