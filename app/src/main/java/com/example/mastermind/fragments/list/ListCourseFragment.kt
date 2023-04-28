package com.example.mastermind.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.R
import com.example.mastermind.data.CourseViewModel
import com.example.mastermind.databinding.FragmentListCourseBinding
import com.example.roomapp.fragments.list.CourseListAdapter

class ListCourseFragment : Fragment() {

    private lateinit var binding: FragmentListCourseBinding
    private lateinit var courseViewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCourseBinding.inflate(inflater, container, false)

        val adapter = CourseListAdapter()
        binding.rvCourses.adapter = adapter
        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())

        courseViewModel = ViewModelProvider(this)[CourseViewModel::class.java]
        courseViewModel.getAllCourse().observe(viewLifecycleOwner, Observer { course ->

            adapter.setData(course)

        })

        binding.fabAddCourse.setOnClickListener{
            findNavController().navigate(R.id.actionFromListToAddCourse)
        }

        return binding.root
    }

}