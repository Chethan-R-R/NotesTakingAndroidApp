package com.example.notestakingapp

import android.net.Uri

object GlobalData{
    var user_id:Long=0
    var noteIds= mutableListOf<Int>()
    var titleList= mutableListOf<String>()
    var descriptionList= mutableListOf<String>()
    var imgList= mutableListOf<MutableList<Uri?>>()
}