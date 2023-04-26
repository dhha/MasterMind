package com.example.mastermind.ui.model

import androidx.room.*

@Dao
interface ScheduleDao {
    @Query("Select * from schedules")
    suspend fun getAllSchedule(): List<Schedule>
    @Insert
    suspend fun addSchedule(schedule: Schedule)
    @Delete
    suspend fun deleteSchedule(schedule: Schedule)
    @Update
    suspend fun updateSchedule(schedule: Schedule)
}