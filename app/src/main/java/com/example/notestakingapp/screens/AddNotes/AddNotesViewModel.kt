package com.example.notestakingapp.screens.AddNotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.example.notestakingapp.DataBaseOperations
import com.example.notestakingapp.GlobalData
import com.example.notestakingapp.R
import java.io.File
import java.io.FileOutputStream

class AddNotesViewModel(private val context: Context):ViewModel() {
    private var dataBaseOperations: DataBaseOperations
    private val tempImageList = mutableListOf<Uri?>()
    init {
        dataBaseOperations = DataBaseOperations(context)
    }
    fun insertNotes(title:String,description:String){
        val noteId=dataBaseOperations.addNotes(
            title,
            description,
            GlobalData.user_id
        )
        dataBaseOperations.addImageUri(tempImageList,noteId)
    }


    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?):Uri?{
        if(data?.data!=null) {
            val bitmap: Bitmap =
                MediaStore.Images.Media.getBitmap(context.contentResolver, data?.data)
            val fileName = "notesimage_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
            tempImageList.add(file.toUri())
            return file.toUri()
        }
        return null
    }
}