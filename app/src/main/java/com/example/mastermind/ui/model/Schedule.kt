package com.example.mastermind.ui.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable
import java.sql.Time
import java.util.Date

@Entity(tableName = "schedules")
data class Schedule(var course: String, val name: String, val description: String, val location: String, val date: String, val time: String,
    var audio: Int? = null, val image: Int? = null, val file: String? = null) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
