package com.example.studentnotes

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentUpdateBinding
import com.example.mastermind.ui.model.NoteViewModel
import com.example.mastermind.ui.model.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var noteViewModel: NoteViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

         binding.editText.setText(args.currentNote.title)
        binding.multiLineText.setText(args.currentNote.desc)


         noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding.cancelButton.setOnClickListener{

            findNavController().navigate(R.id.action_updateFragment_to_noteFragmentRecycler)
        }

        binding.saveButton.setOnClickListener{
            updateItem()
            findNavController().navigate(R.id.action_updateFragment_to_noteFragmentRecycler)
        }

        binding.deleteButton.setOnClickListener{
            updateDelItem()
            binding.editText.setText("")
            binding.multiLineText.setText("")
            binding.textView3.text=args.currentNote.status.toString()
            findNavController().navigate(R.id.action_updateFragment_to_noteFragmentRecycler)
        }


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateItem(){
        val title = binding.editText.text.toString()
        val desc = binding.multiLineText.text.toString()
       val date = Date().toString()


        val updateNote = Notes(args.currentNote.id,title,desc,args.currentNote.status,date)
        noteViewModel.updateNote(updateNote)
        Toast.makeText(requireContext(),"Updated Successfully",Toast.LENGTH_LONG).show()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateDelItem(){

        val date = Date().toString()

        val updateNote = Notes(args.currentNote.id,args.currentNote.title,args.currentNote.desc,false,date)
        noteViewModel.updateNote(updateNote)
        Toast.makeText(requireContext(),"Deleted Successfully",Toast.LENGTH_LONG).show()
        // Delete the note after a delay of 5 seconds
        CoroutineScope(Dispatchers.Main).launch {
            delay(60000L)
            noteViewModel.delNotes(updateNote)
           // Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show()
        }


    }


}