package com.example.notestakingapp.screens.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notestakingapp.DataBaseOperations

class RegisterViewModelFactory(private val dataBaseOperations: DataBaseOperations):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterVIewModel::class.java)){
            return RegisterVIewModel(dataBaseOperations) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}