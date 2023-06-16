package com.example.notestakingapp.screens.Login

import androidx.lifecycle.ViewModel
import com.example.notestakingapp.DataBaseOperations
import com.example.notestakingapp.GlobalData

class LoginViewModel(val dataBaseOperations:DataBaseOperations):ViewModel() {
        init {
    }
    fun findUser(userName:String,userPassword:String):String{
        val userFound=dataBaseOperations.findUser(userName)
        if (userFound!=null && userFound.password==userPassword) {
            GlobalData.user_id = userFound.id
            return "Login Successfully"
        }
        else if(userFound!=null){
            return "Incorrect Password"
        }else return "User Not Found"
    }
}