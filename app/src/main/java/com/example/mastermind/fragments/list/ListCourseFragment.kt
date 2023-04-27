package com.example.mastermind.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentListCourseBinding

class ListCourseFragment : Fragment() {

    private var _binding:FragmentListCourseBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentListCourseBinding.inflate(inflater,container,false);

        val view = binding.root;

        binding.fabAddCourse.setOnClickListener{

            Log.d("MAIN", "FAB Clicked")
            Toast.makeText(context, "FAB Clicked", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.actionFromListToAddCourse)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}