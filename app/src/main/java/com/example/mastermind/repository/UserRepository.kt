package com.example.mastermind.repository

import androidx.lifecycle.LiveData
import com.example.mastermind.data.UserDao
import com.example.mastermind.model.User

class UserRepository(private val userDao: UserDao) {

    val getAllUsers : LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}