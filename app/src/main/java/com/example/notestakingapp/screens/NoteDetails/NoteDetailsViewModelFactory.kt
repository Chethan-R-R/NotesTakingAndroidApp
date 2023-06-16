package com.example.notestakingapp.screens.NoteDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notestakingapp.DataBaseOperations
import java.lang.IllegalArgumentException

class NoteDetailsViewModelFactory(private val dataBaseOperations: DataBaseOperations):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailsViewModel::class.java)){
            return NoteDetailsViewModel(dataBaseOperations) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}