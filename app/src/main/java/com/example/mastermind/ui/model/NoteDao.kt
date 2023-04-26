package com.example.mastermind.ui.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.studentnotes.utils.Constants.NOTES_TABLE

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Notes)

//    @Query("SELECT * FROM  $NOTES_TABLE ")
//     fun  getAllNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM $NOTES_TABLE WHERE status = 1")
    fun getNotesWithTrueStatus(): LiveData<List<Notes>>

    @Update(onConflict =OnConflictStrategy.REPLACE )
    suspend fun updateNote(note:Notes)

    @Query("SELECT * FROM $NOTES_TABLE WHERE status = 0")
    fun getNotesWithFalseStatus(): LiveData<List<Notes>>

    @Query("SELECT * FROM $NOTES_TABLE WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchNotes(query: String): LiveData<List<Notes>>

    @Query("SELECT COUNT(*) FROM $NOTES_TABLE")
    fun getNoteCount(): Int

    @Delete
    suspend fun delete(note:Notes)

//    @Query("SELECT * FROM $NOTES_TABLE WHERE title = :title AND description = :description")
//    fun getNoteByTitleAndDescription(title: String, description: String): Notes?
}


