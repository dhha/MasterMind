package com.example.mastermind.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentCreateCourseBinding

class CreateCourseFragment : Fragment() {
    private lateinit var binding: FragmentCreateCourseBinding
    private lateinit var viewModel: CreateCourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_course, container, false)
        binding = FragmentCreateCourseBinding.bind(view)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateCourseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}