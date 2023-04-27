package com.example.mastermind.ui.Grade

import androidx.room.*

@Dao
interface GradeDao {
    @Query("SELECT * FROM Grade")
    suspend fun getAllGrade(): List<Grade>

    @Query("SELECT * FROM Grade WHERE course LIKE '%' || :searchQuery || '%'")
    suspend fun search(searchQuery: String): List<Grade>

    @Insert
    suspend fun addGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)

    @Update
    suspend fun updateGrade(grade: Grade)
}
