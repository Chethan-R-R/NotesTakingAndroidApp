package com.example.notestakingapp.screens.NoteDetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notestakingapp.DataBaseOperations
import com.example.notestakingapp.GlobalData
import com.example.notestakingapp.NavUpdater
import com.example.notestakingapp.R
import com.example.notestakingapp.databinding.FragmentNoteDetailsBinding


class NoteDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailsBinding

    private lateinit var viewModel: NoteDetailsViewModel
    private lateinit var navUpdater: NavUpdater
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navUpdater = context as NavUpdater
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)
        viewModel=ViewModelProvider(this,NoteDetailsViewModelFactory(DataBaseOperations(requireContext())))[NoteDetailsViewModel::class.java]
        try {
            navUpdater.updateForAddNew()
        } catch (err: Exception) {
            Log.i("NoteDetails",err.toString())
        }

        val args = NoteDetailsFragmentArgs.fromBundle(requireArguments())
        binding.noteTitle.text = GlobalData.titleList[args.index]
        binding.editNoteTitle.setText(GlobalData.titleList[args.index])
        binding.noteDescription.text = GlobalData.descriptionList[args.index]
        binding.editNoteDescription.setText(GlobalData.descriptionList[args.index])
        for ((index, imgUri) in GlobalData.imgList[args.index].withIndex()) {
                val imgLayout = layoutInflater.inflate(R.layout.imageview, binding.noteImageGrid, false)
                imgLayout.findViewById<ImageView>(R.id.imgView).setImageURI(imgUri)

                imgLayout.setOnClickListener {
                    it.findNavController().navigate(
                        NoteDetailsFragmentDirections.actionNoteDetailsFragmentToDisplayImageFragment(
                            args.index,
                            index
                        )
                    )
                }
                binding.noteImageGrid.addView(imgLayout)
        }
        var isTitleEditEnabled = false
        binding.enableEditTitle.setOnClickListener {
            if (isTitleEditEnabled) {
                viewModel.updateNote(args.index,binding.editNoteTitle.text.toString(),null)
                GlobalData.titleList[args.index]=binding.editNoteTitle.text.toString()
                binding.noteTitle.text = GlobalData.titleList[args.index]
                binding.editNoteTitle.visibility = View.GONE
                binding.noteTitle.visibility = View.VISIBLE
                binding.enableEditTitle.setImageResource(R.drawable.pencil)
                isTitleEditEnabled = false
            } else {
                binding.editNoteTitle.visibility = View.VISIBLE
                binding.noteTitle.visibility = View.GONE
                binding.enableEditTitle.setImageResource(R.drawable.tick)
                isTitleEditEnabled = true
            }
        }
        var isDescriptionEditEnabled = false
        binding.enableEditDescription.setOnClickListener {
            if (isDescriptionEditEnabled) {
                viewModel.updateNote(args.index,null,binding.editNoteDescription.text.toString())
                GlobalData.descriptionList[args.index]=binding.editNoteDescription.text.toString()
                binding.noteDescription.text = GlobalData.descriptionList[args.index]
                binding.editNoteDescription.visibility = View.GONE
                binding.noteDescription.visibility = View.VISIBLE
                binding.enableEditDescription.setImageResource(R.drawable.pencil)
                isDescriptionEditEnabled = false
            } else {
                binding.editNoteDescription.visibility = View.VISIBLE
                binding.noteDescription.visibility = View.GONE
                binding.enableEditDescription.setImageResource(R.drawable.tick)
                isDescriptionEditEnabled = true
            }
        }
        return binding.root
    }

}