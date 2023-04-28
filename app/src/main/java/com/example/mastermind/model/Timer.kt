package com.example.mastermind.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "timer_table")
data class Timer(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val courseCode : String,
    val timerTitle : String,
    @TypeConverters(Converters::class)
    val timerDate : String,
    @TypeConverters(Converters::class)
    val timerStart : String,
    @TypeConverters(Converters::class)
    val timerEnd : String
): Parcelable