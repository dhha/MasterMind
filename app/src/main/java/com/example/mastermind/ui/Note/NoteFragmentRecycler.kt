package com.example.mastermind.ui.Note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentNoteRecyclerBinding
import com.example.mastermind.ui.adapter.NoteAdapter
import com.example.mastermind.ui.model.NoteViewModel



class NoteFragmentRecycler : Fragment() {


    private lateinit var binding: FragmentNoteRecyclerBinding
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteRecyclerBinding.inflate(inflater, container, false)

        val adapter = NoteAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)

        })



        binding.btnCancel.setOnClickListener {
           // findNavController().navigate(R.id.action_noteFragmentRecycler_to_mainFragment)
            findNavController().navigate(R.id.action_noteFragmentRecycler_to_nav_note)
        }


        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    noteViewModel.searchNotes("%$newText%").observe(viewLifecycleOwner, Observer { notes ->
                        adapter.setData(notes)
                    })
                }
                return true
            }
        })

        return binding.root
    }


}


