package com.example.notestakingapp.screens.NotesList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.notestakingapp.DataBaseOperations
import com.example.notestakingapp.GlobalData
import com.example.notestakingapp.NavUpdater
import com.example.notestakingapp.R
import com.example.notestakingapp.databinding.FragmentNotesListBinding


class NotesListFragment : Fragment() {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var dataBaseOperations: DataBaseOperations
    private lateinit var navUpdater: NavUpdater
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navUpdater = context as NavUpdater
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        dataBaseOperations = DataBaseOperations(requireContext())
        dataBaseOperations.findNotes()
        navUpdater.updateForNoteslist()

        for ((index, title) in GlobalData.titleList.withIndex()) {
            val card = layoutInflater.inflate(R.layout.single_note_card, binding.container, false)
            card.findViewById<TextView>(R.id.cardTitle).text = title
            card.findViewById<TextView>(R.id.cardDescription).text =
                GlobalData.descriptionList[index]
            card.setOnClickListener {
                it.findNavController().navigate(
                    NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(
                        index
                    )
                )
            }
            binding.container.addView(card)
        }

        return binding.root
    }

}