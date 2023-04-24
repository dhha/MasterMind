package com.example.mastermind.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity
data class Schedule(val course: Course, val name:String, val description: String, val date: Date, val time: Time) : java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
