package com.example.notestakingapp.screens.NoteDetails

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.notestakingapp.DataBaseOperations
import com.example.notestakingapp.GlobalData

class NoteDetailsViewModel(private val dataBaseOperations: DataBaseOperations) : ViewModel() {

    fun updateNote(index:Int,title:String?,description:String?){
        dataBaseOperations.updateNote(GlobalData.noteIds[index],title, description)

    }
}