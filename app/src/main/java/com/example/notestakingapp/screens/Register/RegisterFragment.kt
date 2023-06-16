package com.example.notestakingapp.screens.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notestakingapp.DataBaseOperations
import com.example.notestakingapp.R
import com.example.notestakingapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var viewModel: RegisterVIewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegisterBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this,RegisterViewModelFactory(DataBaseOperations(requireContext())))[RegisterVIewModel::class.java]

        val loginRegisterVIew=layoutInflater.inflate(R.layout.loginregister,binding.layout,false)

        loginRegisterVIew.findViewById<TextView>(R.id.title).text="Register"
        val submit=loginRegisterVIew.findViewById<Button>(R.id.submit)
        val registerName=loginRegisterVIew.findViewById<EditText>(R.id.registerName)
        val registerPassword=loginRegisterVIew.findViewById<EditText>(R.id.registerPassword)

        submit.text="Register"
        submit.setOnClickListener {
            val userAdded:String=viewModel.addUser(registerName.text.toString(),registerPassword.text.toString())
            Toast.makeText(context,userAdded,Toast.LENGTH_SHORT).show()
            if(userAdded=="Registered successfully")it.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        binding.layout.addView(loginRegisterVIew)
        return binding.root
    }

}