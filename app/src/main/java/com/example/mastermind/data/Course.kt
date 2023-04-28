package com.example.mastermind.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "course_table")
data class Course(
    @PrimaryKey
    val code : String,
    val courseName : String,
    val courseDescription : String,
    val faculty : String,
    val classroom : String
)