package com.example.notestakingapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.util.Log
import androidx.core.database.getStringOrNull
import androidx.core.net.toUri

class DataBaseOperations(context:Context){
    private val dataBase=DataBase(context)
    fun addUser(userName:String,password:String):String{
        val db=dataBase.writableDatabase
        val values=ContentValues().apply {
            put("name",userName)
            put("password",password)
        }
        if(password=="")return "Fill password field"
        try{
            db.insertOrThrow("users",null,values)
            return "Registered successfully"
        }catch (err:Exception){
            return "User Name already exist"
        }

        db.close()
    }
    fun addNotes(title: String,description: String,id:Long):Long{
        val db=dataBase.writableDatabase
        var NoteId:Long=0
        val values=ContentValues().apply {
            put("title",title)
            put("description",description)
            put("user_id",id)
        }
        try{
            NoteId=db.insert("notes",null,values)
        }catch (err:Exception){

        }
        return NoteId

        db.close()
    }
    fun addImageUri(imageUri:MutableList<Uri?>,id:Long){
        val db=dataBase.writableDatabase
        for(uri in imageUri){
            val values=ContentValues().apply {
                put("imgUri",uri.toString())
                put("notes_id",id)
            }
            db.insert("images",null,values)
        }
        db.close()
    }
    fun updateNote(notesId:Int,title:String?=null,description: String?=null){
        val db=dataBase.writableDatabase
        val values=ContentValues().apply {
            if(title!=null)put("title",title)
            if(description!=null)put("description",description)
        }
        db.update("notes",values,"id = ?", arrayOf(notesId.toString()))
        db.close()
    }
    fun findUser(userName:String):UserData?{
        val db=dataBase.readableDatabase
        val cursor=db.query("users",null,"name = ?", arrayOf(userName),null,null,null)
        var user:UserData?=null
        if(cursor.moveToFirst()){
            val userId=cursor.getLong(cursor.getColumnIndexOrThrow("id"))
            val userName=cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val password=cursor.getString(cursor.getColumnIndexOrThrow("password"))
            user= UserData(userId,userName,password)
        }
        cursor.close()
        db.close()
        return user
    }
    fun findNotes(){
        val db=dataBase.readableDatabase
        val cursor=db.query("notes",null,"user_id = ?", arrayOf(GlobalData.user_id.toString()),null,null,null)
        GlobalData.imgList= mutableListOf()
        GlobalData.titleList= mutableListOf()
        GlobalData.descriptionList= mutableListOf()
        GlobalData.noteIds= mutableListOf()
        while (cursor.moveToNext()){
            val id=cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val cursorImg=db.query("images",null,"notes_id = ?", arrayOf(id.toString()),null,null,null)
            //val cursorImg=db.rawQuery("SELECT imgUri FROM images WHERE notes_id = ?", arrayOf(id.toString()))
            val uriList= mutableListOf<Uri?>()
            while (cursorImg.moveToNext()){
                val imgUri=cursorImg.getString(cursorImg.getColumnIndexOrThrow("imgUri"))
                uriList.add(Uri.parse(imgUri))
            }
            val title=cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val description=cursor.getString(cursor.getColumnIndexOrThrow("description"))
            GlobalData.imgList.add(uriList)
            GlobalData.titleList.add(title)
            GlobalData.descriptionList.add(description)
            GlobalData.noteIds.add(id)

        }
        cursor.close()
        db.close()
    }
}