package com.example.notestakingapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException

class DataBase(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME="userdata.db"
        private val DATABASE_VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createUserTable="CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT UNIQUE  ,password TEXT)"
        db?.execSQL(createUserTable)
        val createNotesTable="CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,description TEXT,user_id INTEGER,FOREIGN KEY(user_id) REFERENCES users(id) )"
        try {
            db?.execSQL(createNotesTable)
        }catch (err:Exception){
        }

        val createImagesTable="CREATE TABLE images (id INTEGER PRIMARY KEY AUTOINCREMENT,imgUri TEXT,notes_id INTEGER,FOREIGN KEY(notes_id) REFERENCES notes(id))"
        db?.execSQL(createImagesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        db?.execSQL("DROP TABLE IF EXISTS notes")
        db?.execSQL("DROP TABLE IF EXISTS images")
        onCreate(db)
    }
}