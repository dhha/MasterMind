package com.example.mastermind.ui.Note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentNoteDelRecyclerBinding
import com.example.mastermind.ui.adapter.NoteAdapterDel
import com.example.mastermind.ui.model.NoteViewModel
import com.example.studentnotes.SwipeToDeleteCallback

import java.util.*


class NoteFragmentDelRecycler : Fragment() {
  private lateinit var binding: FragmentNoteDelRecyclerBinding

    private lateinit var noteViewModel: NoteViewModel


//    init{
//        noteViewModel.getDelNotes(status = false)
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentNoteDelRecyclerBinding.inflate(inflater, container, false)

        val adapter = NoteAdapterDel()
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())


    val swapToDeleteCallback = object : SwipeToDeleteCallback(){
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val note = adapter.getData()[position]
            note.status=true
            note.date= Date().toString()
            noteViewModel.updateNote(note)
            adapter.removeItem(position)
            Toast.makeText(requireContext(), "Note Restored", Toast.LENGTH_SHORT).show()
        }

    }
// Create a new ItemTouchHelper instance with the SwipeToDeleteCallback
     val itemTouchHelper = ItemTouchHelper(swapToDeleteCallback)
    // Attach the ItemTouchHelper to the RecyclerView
     itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getNotesByStatus().observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)

        })

        binding.btnCancel.setOnClickListener{
           // findNavController().navigate(R.id.action_noteFragmentDelRecycler_to_mainFragment)
            findNavController().navigate(R.id.action_noteFragmentDelRecycler_to_nav_note)

        }


    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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