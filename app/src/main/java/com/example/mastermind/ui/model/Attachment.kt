package com.example.mastermind.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Attachment(val name: String, val url: Int): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
