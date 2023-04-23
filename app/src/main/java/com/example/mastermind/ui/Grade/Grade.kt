package com.example.mastermind.ui.Grade

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="grades")
data class Grade(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val course: String,
    val quizScore: Double,
    val midScore: Double,
    val assignmentScore: Double,
    val finalScore: Double,
    val calculatedGrade: String
)
