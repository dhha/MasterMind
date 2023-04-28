package com.example.mastermind.fragments.add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentAddTimerBinding
import com.example.mastermind.model.Course
import com.example.mastermind.model.Timer
import com.example.mastermind.viewmodel.TimerViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTimerFragment : Fragment() {

    private lateinit var binding : FragmentAddTimerBinding
    private val args by navArgs<AddTimerFragmentArgs>()
    private lateinit var course : Course
    private lateinit var timerViewModel: TimerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddTimerBinding.inflate(inflater,container,false);

        val view = binding.root
        course = args.selectedCourse
        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        val calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{
            view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateTvTimerDate(calendar)
        }

        binding.btnSaveTimer.setOnClickListener{
            addTimerToDB()
        }

        binding.tvTimerDate.setOnClickListener{
            DatePickerDialog(
                requireContext(),
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.tvTimerStartTime.setOnClickListener{
            val startTimeCalendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                startTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                startTimeCalendar.set(Calendar.MINUTE, minute)
                binding.tvTimerStartTime.text = SimpleDateFormat("HH:mm").format(startTimeCalendar.time)
            }

            TimePickerDialog(requireContext(), timeSetListener, startTimeCalendar.get(Calendar.HOUR_OF_DAY),
            startTimeCalendar.get(Calendar.MINUTE), false).show()
        }

        binding.tvTimerEndTime.setOnClickListener{
            val endTimeCalendar = Calendar.getInstance()
            val endTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                endTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                endTimeCalendar.set(Calendar.MINUTE, minute)
                binding.tvTimerEndTime.text = SimpleDateFormat("HH:mm").format(endTimeCalendar.time)
            }

            TimePickerDialog(requireContext(), endTimeSetListener, endTimeCalendar.get(Calendar.HOUR_OF_DAY),
                endTimeCalendar.get(Calendar.MINUTE), false).show()
        }

        return view
    }

    private fun updateTvTimerDate(cal : Calendar){
        val dateFormat = "dd-MM-yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.US)
        binding.tvTimerDate.text = simpleDateFormat.format(cal.time)
    }

    fun addTimerToDB(){
        val timerTitle = binding.etTimerTitle.text.toString()
        val timerDate = binding.tvTimerDate.text.toString()
        val timerStart = binding.tvTimerStartTime.text.toString()
        val timerEnd = binding.tvTimerEndTime.text.toString()

        if (validateTimerInputs(timerTitle, timerDate, timerStart, timerEnd)){
            val timer = Timer(0, course.code, timerTitle, timerDate, timerStart, timerEnd)
            timerViewModel.addTimer(timer)
            Toast.makeText(requireContext(), "Timer successfully created.", Toast.LENGTH_LONG).show()


            val action = AddTimerFragmentDirections.actionFromAddTimerToListTimer(course)
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    fun validateTimerInputs(timerTitle : String, timerDate: String, timerStart : String, timerEnd : String): Boolean {
        return !(TextUtils.isEmpty(timerTitle) &&
                TextUtils.isEmpty(timerDate) &&
                TextUtils.isEmpty(timerStart) &&
                TextUtils.isEmpty(timerEnd))
    }

}