package com.example.mastermind.ui.model

import android.net.Uri
import androidx.room.*
import java.io.Serializable
import java.sql.Time
import java.util.Date

@Entity(tableName = "schedules")
data class Schedule(var course: String, var name: String, var description: String, var location: String, var date: String, var time: String,
                    var audio: String? = null, var image: String? = null, var file: String? = null) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
