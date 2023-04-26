package com.example.mastermind.ui.Note

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentAddBinding
import com.example.mastermind.ui.model.NoteViewModel
import com.example.mastermind.ui.model.Notes

import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date


class AddFragment : Fragment() {

private lateinit var binding: FragmentAddBinding
private lateinit var noteViewModel: NoteViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.btnSave.setOnClickListener {

            addNote()
            findNavController().navigate(R.id.action_addFragment_to_nav_note)

        }

        return binding.root
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNote() {

        val title = binding.title.text.toString()
        val desc = binding.desc.text.toString()
        val status = true
        val  date = Date().toString()
        if(title.equals("Title") || desc.equals("Description")){
            val snackbar = Snackbar.make(binding.btnSave, "Please Enter a Title and a Description", Snackbar.LENGTH_LONG)
            snackbar.show()
        }else{


            lifecycleScope.launch{
             val notes = Notes(0,title, desc, status, date)
               // NotesDatabase(requireContext()).getDao().addNote(notes)
                noteViewModel.addNote(notes)
                Toast.makeText(requireContext(), "Successfully added",Toast.LENGTH_LONG).show()
                Log.d("DetailFragment", "Note inserted with ID: $id")
            }
        }
    }

}