package com.example.mastermind.fragments.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mastermind.databinding.FragmentAddCourseBinding

class AddCourseFragment : Fragment() {

    private var _binding: FragmentAddCourseBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCourseBinding.inflate(inflater,container,false);

        val view = binding.root;

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}