package com.example.mastermind.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.databinding.FragmentListTimerBinding
import com.example.mastermind.model.Course
import com.example.mastermind.viewmodel.TimerViewModel

class ListTimerFragment : Fragment() {

    private lateinit var binding: FragmentListTimerBinding

    private lateinit var timerViewModel: TimerViewModel

    private val args by navArgs<ListTimerFragmentArgs>()

    private lateinit var course : Course

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListTimerBinding.inflate(inflater, container, false)

        course = args.selectedCourse

        val adapter = TimerListAdapter()

        binding.rvTimers.adapter = adapter

        binding.rvTimers.layoutManager = LinearLayoutManager(requireContext())

        timerViewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        timerViewModel.getCourseTimers(course.code).observe(viewLifecycleOwner, Observer { ti ->

            adapter.setData(ti)

        })

        binding.fabAddTimer.setOnClickListener{
            val action = ListTimerFragmentDirections.actionFromListTimerToAddTimer(course)
            findNavController().navigate(action)

        }
        return binding.root
    }
}