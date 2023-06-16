package com.example.notestakingapp.screens.Login

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.notestakingapp.NavUpdater
import com.example.notestakingapp.R
import com.example.notestakingapp.databinding.FragmentLoginBinding
import java.lang.Exception

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel:LoginViewModel
    private  var navUpdater: NavUpdater?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navUpdater=context as NavUpdater
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentLoginBinding.inflate(inflater,container,false)
            viewModel= ViewModelProvider(this,LoginViewModelFactory(DataBaseOperations(requireContext())))[LoginViewModel::class.java]

            val loginRegisterView=layoutInflater.inflate(R.layout.loginregister,binding.layout,false)

            loginRegisterView.findViewById<TextView>(R.id.title).text="Log In"
            val userName=loginRegisterView.findViewById<EditText>(R.id.registerName)
            val userPassword=loginRegisterView.findViewById<EditText>(R.id.registerPassword)
            val submit=loginRegisterView.findViewById<Button>(R.id.submit)

            submit.text="Login"
            submit.setOnClickListener {
                try{
                    val login=viewModel.findUser(userName.text.toString(),userPassword.text.toString())
                    if (login=="Login Successfully"){
                        Toast.makeText(context,"" +
                                "Welcome Back",Toast.LENGTH_LONG).show()
                        it.findNavController().navigate(R.id.notesListFragment)
                    }else Toast.makeText(context,login,Toast.LENGTH_SHORT).show()
                }catch (err:Exception){
                    Log.i("lodalussan",err.toString())
                }

            }

            binding.layout.addView(loginRegisterView)

        try {
            navUpdater?.updateForLogin()
        }catch (err:Throwable){}
        return binding.root
    }

}



