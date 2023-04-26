package com.example.mastermind.ui.model;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Formatter;

public class Converters {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }



}
