package com.example.mastermind.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Courses")
data class Course(val couserName: String, val description: String, val professor: String, val location: String) : java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
