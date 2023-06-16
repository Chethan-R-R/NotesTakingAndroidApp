package com.example.notestakingapp.screens.AddNotes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddNotesViewModelFactory(private val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddNotesViewModel::class.java)){
            return AddNotesViewModel(context) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}