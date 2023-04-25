package com.example.mastermind.ui.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentCreateScheduleBinding
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

        binding.btnCreateCourse.setOnClickListener {
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToCreateCourseFragment()
            findNavController().navigate(direction)
        }

        binding.fragmentTaskAddAudioAttachment.setOnClickListener {
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToAudioRecordFragment()
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