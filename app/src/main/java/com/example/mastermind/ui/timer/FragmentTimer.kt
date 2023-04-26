package com.example.mastermind.ui.timer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.CourseListAdapter
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentFragmenTimerBinding
import com.example.mastermind.databinding.FragmentHomeBinding
import com.example.mastermind.ui.adapter.ScheduleAdapter
import com.example.mastermind.ui.home.HomeFragmentDirections
import com.example.mastermind.ui.home.HomeViewModel
import com.example.mastermind.ui.model.MasterMindDatabase
import kotlinx.coroutines.launch

class FragmentTimer : Fragment() {

    private lateinit var binding : FragmentFragmenTimerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = FragmentFragmenTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Get all schedule
        lifecycleScope.launch {
            context?.let {
                var courses = MasterMindDatabase(it).getCourseDao().getAllCourses()
                val adapter = courses?.let {
                    CourseListAdapter(courses)
                }

                binding.myRecyclerView.layoutManager = LinearLayoutManager(it)
                binding.myRecyclerView.adapter = adapter
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}