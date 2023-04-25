package com.example.mastermind.ui.Grade

import androidx.room.*

@Dao
interface GradeDao {
    @Query("SELECT*FROM Grade")
    suspend fun getAllGrade():List<Grade>
    @Insert
    suspend fun addGrade(grade:Grade)
    @Delete
    suspend fun deleteGrade(grade:Grade)
    @Update
    suspend fun updateGrade(grade: Grade)
}