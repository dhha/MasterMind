package com.example.mastermind.ui.model;

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteRepository(private val noteDao:NoteDao) {

    //val readAllData : LiveData<List<Notes>> = noteDao.getAllNotes()
     val readAllData : LiveData<List<Notes>> = noteDao.getNotesWithTrueStatus()


    //val readAllDelData : Notes = noteDao.getNoteByStatus(status = false)

    suspend fun addNote(note:Notes ){

        noteDao.addNote(note)
    }


    suspend fun updateNote(note:Notes){
        noteDao.updateNote(note)
    }

    fun getNoteByStatus(): LiveData<List<Notes>> {
        return noteDao.getNotesWithFalseStatus()
    }

    fun searchNotes(query: String): LiveData<List<Notes>> {
        return noteDao.searchNotes(query)
        }

    fun getNoteCount():Int {
        return noteDao.getNoteCount()
    }

    suspend fun delNotes(note:Notes){
        noteDao.delete(note)
    }
}