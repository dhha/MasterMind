package com.example.mastermind.ui.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Time
import java.util.Date

@Entity
data class Schedule(var course: String, val name: String, val description: String, val location: String, val date: String, val time: String,
    var audio: Int, val image: Int, val file: String) : java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
