package com.example.mastermind.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Attachment(val name: String, val url: Int): java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
