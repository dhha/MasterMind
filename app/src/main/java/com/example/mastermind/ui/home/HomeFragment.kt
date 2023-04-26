package com.example.mastermind.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.databinding.FragmentHomeBinding
import com.example.mastermind.ui.adapter.ScheduleAdapter
import com.example.mastermind.ui.model.MasterMindDatabase
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Get all schedule
        lifecycleScope.launch {
            context?.let {
                var schedules = MasterMindDatabase(it).getScheduleDao().getAllSchedule()
                val adapter = schedules?.let {
                    ScheduleAdapter(schedules)
                }

                binding.listSchedule.layoutManager = LinearLayoutManager(it)
                binding.listSchedule.adapter = adapter
            }
        }

        binding.activityHomeFab.setOnClickListener {
//            val intent = Intent(requireContext(), CreateScheduleFragment::class.java)
//            startActivity(intent)
            val direction = HomeFragmentDirections.actionNavScheduleToCreateScheduleFragment(null)
            findNavController().navigate(direction)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}