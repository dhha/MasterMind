package com.example.mastermind.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val firstName : String,
    val lastName : String,

    val email : String,
    val password : String
)