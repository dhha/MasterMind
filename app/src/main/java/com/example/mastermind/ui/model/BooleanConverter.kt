package com.example.mastermind.ui.model;

import androidx.room.TypeConverter

class BooleanConverter {
    @TypeConverter
    fun fromInt(value: Int): Boolean {
        return value == 1
    }

    @TypeConverter
    fun booleanToInt(value: Boolean): Int {
        return if (value) 1 else 0
    }
}
