package com.example.notestakingapp.screens.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notestakingapp.DataBaseOperations

class LoginViewModelFactory(private val dataBaseOperations: DataBaseOperations):ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataBaseOperations) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}