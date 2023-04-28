package com.example.mastermind.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mastermind.model.Timer

@Dao
interface TimerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTimer(timer: Timer)

    @Query("SELECT * FROM timer_table WHERE courseCode = :courseCode ORDER BY id DESC")
    fun getCourseTimers(courseCode : String) : LiveData<List<Timer>>
}