package com.example.mastermind.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application){

    private val getAllUsers : LiveData<List<User>>
    private val userRepository : UserRepository

    init {

        val userDao = MastermindDatabase.getDatabase(application).userDao()

        userRepository = UserRepository(userDao)

        getAllUsers = userRepository.getAllUsers

    }

    fun addUser(user : User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }
}