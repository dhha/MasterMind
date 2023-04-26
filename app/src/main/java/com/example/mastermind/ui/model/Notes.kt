package com.example.mastermind.ui.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import androidx.versionedparcelable.VersionedParcelize
import com.example.studentnotes.utils.Constants.NOTES_TABLE
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.Date

@Parcelize
@Entity(tableName = NOTES_TABLE )
data class Notes(
    @PrimaryKey(autoGenerate = true)
     var id : Int=0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var desc: String,
    @ColumnInfo(name = "status")
    var status: Boolean,
    @TypeConverters(Converters::class)
    var date: String
):Parcelable


