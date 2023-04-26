package com.example.mastermind.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentCreateCourseBinding
import com.example.mastermind.ui.Grade.GradeDatabase
import com.example.mastermind.ui.Grade.StudentAdapter
import com.example.mastermind.ui.model.Course
import com.example.mastermind.ui.model.MasterMindDatabase
import kotlinx.coroutines.launch

class CreateCourseFragment : Fragment() {
    private lateinit var binding: FragmentCreateCourseBinding
    private lateinit var viewModel: CreateCourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_course, container, false)
        binding = FragmentCreateCourseBinding.bind(view)

        binding.cancelCourse.setOnClickListener {
            val direction = CreateCourseFragmentDirections.actionCreateCourseFragmentToCreateScheduleFragment()
            findNavController().navigate(direction)
        }
        binding.saveCourse.setOnClickListener {
            lifecycleScope.launch {
                context?.let {
                    val name = binding.fragmentCourseName.text.toString()
                    val desc = binding.fragmentCourseDest.text.toString()
                    val location = binding.fragmentCourseLocation.text.toString()
                    val professor = binding.fragmentCourseProfessor.text.toString()

                    var course = Course(name, desc, professor, location)
                    MasterMindDatabase(it).getCourseDao().addCourse(course)
                }
            }

            val direction = CreateCourseFragmentDirections.actionCreateCourseFragmentToCreateScheduleFragment()
            findNavController().navigate(direction)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateCourseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}