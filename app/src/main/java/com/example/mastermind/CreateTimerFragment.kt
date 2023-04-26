package com.example.mastermind

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mastermind.databinding.FragmentCreateTimerBinding

class CreateTimerFragment : Fragment() {

    private lateinit var binding: FragmentCreateTimerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_create_timer, container, false)

        binding = FragmentCreateTimerBinding.bind(view)

        return binding.root
    }


}