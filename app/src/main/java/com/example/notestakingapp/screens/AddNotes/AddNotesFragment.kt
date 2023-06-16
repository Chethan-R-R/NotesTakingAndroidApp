package com.example.notestakingapp.screens.AddNotes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notestakingapp.NavUpdater
import com.example.notestakingapp.R
import com.example.notestakingapp.databinding.FragmentAddNotesBinding

class AddNotesFragment : Fragment() {
    private lateinit var binding: FragmentAddNotesBinding
    private lateinit var viewModel: AddNotesViewModel
    private lateinit var navUpdater: NavUpdater
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navUpdater = context as NavUpdater
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNotesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this,AddNotesViewModelFactory(requireContext()))[AddNotesViewModel::class.java]
        try {
            navUpdater.updateForAddNew()
        } catch (err: Throwable) {
        }

        binding.addImgBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 1)
        }

        binding.saveNote.setOnClickListener {
            viewModel.insertNotes(
                binding.editTitle.text.toString(),
                binding.editDescription.text.toString()
            )
            Toast.makeText(context, "New Note Saved", Toast.LENGTH_SHORT).show()
            it.findNavController()
                .navigate(AddNotesFragmentDirections.actionAddNotesFragmentToNotesListFragment())
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri:Uri? = viewModel.handleActivityResult(requestCode, resultCode, data)
        if(uri!=null){
            val imgLayout = layoutInflater.inflate(R.layout.imageview, binding.imgList, false)
            imgLayout.findViewById<ImageView>(R.id.imgView).setImageURI(uri)
            binding.imgList.addView(imgLayout)
        }
    }
}