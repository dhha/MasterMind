package com.example.mastermind.ui.model

import android.net.Uri
import androidx.room.*
import java.io.Serializable
import java.sql.Time
import java.util.Date

@Entity(tableName = "schedules")
data class Schedule(var course: String, val name: String, val description: String, val location: String, val date: String, val time: String,
    var audio: String? = null,val image: String? = null, val file: String? = null) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
