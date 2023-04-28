package com.example.mastermind.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
@Parcelize
@Entity(tableName = "course_table")
data class Course(
    @PrimaryKey
    val code : String,
    val courseName : String,
    val courseDescription : String,
    val faculty : String,
    val classroom : String
): Parcelable