package com.example.mastermind.ui.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentCreateScheduleBinding
import com.example.mastermind.ui.Grade.GradeDatabase
import com.example.mastermind.ui.Grade.StudentAdapter
import com.example.mastermind.ui.model.Course
import com.example.mastermind.ui.model.MasterMindDatabase
import com.example.mastermind.ui.model.Schedule
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateScheduleFragment : Fragment() {
    private lateinit var binding: FragmentCreateScheduleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_schedule, container, false)
        binding = FragmentCreateScheduleBinding.bind(view)
        //Loading courses
        lifecycleScope.launch {
            context?.let {
                val courses = MasterMindDatabase(it).getCourseDao().getAllCourses()
                binding.listCourses.adapter = SpinnerAdapter(it, courses)
            }
        }

        binding.btnCreateCourse.setOnClickListener {
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToCreateCourseFragment()
            findNavController().navigate(direction)
        }

        binding.fragmentTaskAddAudioAttachment.setOnClickListener {
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToAudioRecordFragment()
            findNavController().navigate(direction)
        }

        binding.cancelSchedule.setOnClickListener {
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToNavSchedule()
            findNavController().navigate(direction)
        }

        binding.saveSchedule.setOnClickListener {
            lifecycleScope.launch {
                context?.let {
                    val course = binding.listCourses.selectedItem as Course
                    val name = binding.fragmentTaskTitle.text.toString()
                    val desc = binding.fragmentTaskDescription.text.toString()
                    val location = binding.fragmentTaskLocation.text.toString()
                    val date = binding.reminderDate.text.toString()
                    val time = binding.reminderTime.text.toString()

                    val schedule = Schedule(course.couserName, name, desc, location, date, time)
                    MasterMindDatabase(it).getScheduleDao().addSchedule(schedule)
                }
            }
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToNavSchedule()
            findNavController().navigate(direction)
        }

        binding.datePicker.setOnClickListener {
            val c = Calendar.getInstance() // Current Date
            val mYear = c.get(Calendar.YEAR)
            val  mMonth = c.get(Calendar.MONTH)
            val  mDay = c.get(Calendar.DAY_OF_MONTH)
            // Arguments in the below code of  year, monthOfYear, dayOfMonth are picked value from the DataPicker Dialog
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                binding.reminderDate.setText("$dayOfMonth/${monthOfYear+1}/$year")

            }, mYear, mMonth, mDay) // Once you open the dialog it will show the current date and time

            dpd.show()
        }

        binding.timePicker.setOnClickListener {
            val cal = Calendar.getInstance()
            // Arguments in the below code of hour, minute are picked value from the TimePicker Dialog
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                // Selected hour and minutes set into the TextView
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.reminderTime.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }
            // TimePickerDialog will display with current time
            TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), false).show()
        }
        return binding.root
    }

}