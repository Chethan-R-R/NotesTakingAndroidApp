package com.example.notestakingapp.screens.Register

import androidx.lifecycle.ViewModel
import com.example.notestakingapp.DataBaseOperations

class RegisterVIewModel(private val dataBaseOperations: DataBaseOperations):ViewModel() {

    fun addUser(registerName:String,registerPassword:String):String{
        return dataBaseOperations.addUser(registerName,registerPassword)
    }
}