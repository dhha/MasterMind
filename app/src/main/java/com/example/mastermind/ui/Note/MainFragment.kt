package com.example.mastermind.ui.Note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentNoteTakingBinding



class MainFragment : Fragment() {


    private lateinit var binding: FragmentNoteTakingBinding
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentNoteTakingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update the TextView when the Fragment is loaded
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_note_to_addFragment)
        }
        binding.textView1.setOnClickListener {
            findNavController().navigate(R.id.action_nav_note_to_noteFragmentRecycler)
        }

        binding.textView2.setOnClickListener {
            findNavController().navigate(R.id.action_nav_note_to_noteFragmentRecycler)
        }
    }
}
