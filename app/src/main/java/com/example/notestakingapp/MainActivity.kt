 package com.example.notestakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.notestakingapp.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity(),NavUpdater {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.nav1.setOnClickListener {
            findNavController(R.id.navHoster).navigate(R.id.loginFragment)
        }
        binding.nav2.setOnClickListener {
            findNavController(R.id.navHoster).navigate(R.id.registerFragment)
        }
    }
     override fun updateForNoteslist() {
        binding.nav2.visibility=View.GONE
         binding.nav1.visibility=View.VISIBLE
         binding.nav1.text="Add New Note"
         binding.nav1.setOnClickListener {
             findNavController(R.id.navHoster).navigate(R.id.addNotesFragment)
         }
     }
     override fun updateForLogin() {
        binding.nav2.visibility=View.VISIBLE
         binding.nav1.text="Log In"
         binding.nav1.setOnClickListener {
             findNavController(R.id.navHoster).navigate(R.id.loginFragment)
         }
     }
     override fun updateForAddNew() {
         binding.nav1.visibility=View.GONE

     }

 }