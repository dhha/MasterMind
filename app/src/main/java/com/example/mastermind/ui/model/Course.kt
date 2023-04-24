package com.example.mastermind.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(val name: String, val description: String, val professor: String, val location: String) : java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}