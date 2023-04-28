package com.example.mastermind.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.model.Course
import com.example.mastermind.viewmodel.CourseViewModel
import com.example.mastermind.databinding.FragmentAddCourseBinding

class AddCourseFragment : Fragment() {

    private var _binding: FragmentAddCourseBinding? = null;
    private val binding get() = _binding!!;
    private lateinit var courseViewModel : CourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCourseBinding.inflate(inflater,container,false);

        val view = binding.root;

        courseViewModel = ViewModelProvider(this).get(CourseViewModel::class.java)

        binding.btnSaveCourse.setOnClickListener{
            addCourseToDB()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addCourseToDB(){

        val code = binding.etCourseCode.text.toString()
        val courseName = binding.etCourseName.text.toString()
        val courseDescription = binding.etCourseDescription.text.toString()
        val faculty = binding.etFaculty.text.toString()
        val classroom = binding.etClassRoom.text.toString()

        if (validateCourseInputs(code, courseName, courseDescription, faculty, classroom)){
            val course = Course(code, courseName, courseDescription, faculty, classroom)
            courseViewModel.addCourse(course)
            Toast.makeText(requireContext(), "Course successfully added.", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.actionFromAddCourseToListCourses)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    fun validateCourseInputs(code : String, courseName: String, courseDescription : String, faculty : String, classroom : String): Boolean {
        return !(TextUtils.isEmpty(code) &&
                TextUtils.isEmpty(courseName) &&
                TextUtils.isEmpty(courseDescription) &&
                TextUtils.isEmpty(faculty) &&
                TextUtils.isEmpty(classroom))
    }

}