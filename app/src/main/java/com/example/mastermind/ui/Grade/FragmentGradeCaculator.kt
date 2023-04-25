package com.example.mastermind.ui.Grade

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentGradeCaculatorBinding
import com.example.mastermind.myActivity
import kotlinx.coroutines.launch

class FragmentGradeCaculator : BaseFragment() {
private lateinit var binding: FragmentGradeCaculatorBinding

private lateinit var lists:ArrayList<Grade>

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_grade_caculator, container, false)
     binding= FragmentGradeCaculatorBinding.bind(view)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listOfGrade.setHasFixedSize(true)
        binding.listOfGrade.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val grades= GradeDatabase(it).getGradeDao().getAllGrade()
                binding.listOfGrade.adapter=StudentAdapter(grades)
            }
        }

        binding.activityMy.setOnClickListener {
            val direction= FragmentGradeCaculatorDirections.actionNavScheduleToGradeGragment()
            findNavController().navigate(direction)

        }
    }


}