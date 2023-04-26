package com.example.mastermind.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
     val readAllData : LiveData<List<Notes>>

    val notes: LiveData<List<Notes>> = MutableLiveData()


    private val repository : NoteRepository


    init{
        val notedao = MasterMindDatabase(application).getNoteDao()
        repository = NoteRepository(notedao)
        readAllData = repository.readAllData
    }


    fun addNote(note:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }
    fun getNotesByStatus(): LiveData<List<Notes>> {
        return repository.getNoteByStatus()
    }

    fun searchNotes(query: String): LiveData<List<Notes>> {
        return repository.searchNotes(query)
    }

    fun getNoteCount():Int{
        return repository.getNoteCount()
    }

    suspend fun delNotes(note: Notes){
        repository.delNotes(note)
    }
}