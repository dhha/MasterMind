package com.example.mastermind.ui.Grade

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="Grade")
data class Grade(
    val course: String,
    val quizScore: String,
    val midScore: String,
    val assignmentScore: String,
    val finalScore: String,
    val calculatedGrade: String
): Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
