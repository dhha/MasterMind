package com.example.mastermind.ui.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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


class CreateScheduleFragment : Fragment(), AudioRecordFragment.audioDialogListener {
    private lateinit var binding: FragmentCreateScheduleBinding
    private val narg : CreateScheduleFragmentArgs by navArgs()
    lateinit var startforResultGalley : ActivityResultLauncher<Intent>
    private lateinit var scheduleViewModel: CreateScheduleViewModel
    private var courses: kotlin.collections.List<Course>? = null
    private var schedule: Schedule? = null
    private var isPlaying = false
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_schedule, container, false)
        binding = FragmentCreateScheduleBinding.bind(view)
        scheduleViewModel = ViewModelProvider(this).get(CreateScheduleViewModel::class.java)
        scheduleViewModel.setAudioPath(narg.path)
        scheduleViewModel.setSchedule(narg.schedule)

        scheduleViewModel.course.observe(viewLifecycleOwner) {courseName ->
            lifecycleScope.launch {
                context?.let {
                    val course = MasterMindDatabase(requireContext()).getCourseDao().findCouser(courseName)
                    if(course != null && courses != null && courses?.indexOf(course)!! >= 0) {
                        binding.listCourses.setSelection(courses!!.indexOf(course))
                    }
                }
            }
        }

        scheduleViewModel.title.observe(viewLifecycleOwner) {
            binding.fragmentTaskTitle.setText(it)
        }

        scheduleViewModel.description.observe(viewLifecycleOwner) {
            binding.fragmentTaskDescription.setText(it)
        }

        scheduleViewModel.location.observe(viewLifecycleOwner) {
            binding.fragmentTaskLocation.setText(it)
        }

        scheduleViewModel.date.observe(viewLifecycleOwner) {
            binding.reminderDate.setText(it)
        }

        scheduleViewModel.time.observe(viewLifecycleOwner) {
            binding.reminderTime.setText(it)
        }

        scheduleViewModel.audio.observe(viewLifecycleOwner) {
            if(it == null) {
                binding.itemAttachmentAudioContainer.visibility = View.INVISIBLE
            } else {
                binding.itemAttachmentAudioContainer.visibility = View.VISIBLE
            }
        }

        scheduleViewModel.image.observe(viewLifecycleOwner) {
            if(it.toString().equals("null")) {
                binding.itemAttachmentImageContainer.visibility = View.INVISIBLE
            } else {
                binding.itemAttachmentImageContainer.visibility = View.VISIBLE
                binding.itemAttachmentImageContent.setImageURI(it)
            }
        }

        scheduleViewModel.file.observe(viewLifecycleOwner) {
            if(it == null) {
                binding.itemAttachmentLinkContainer.visibility = View.INVISIBLE
            } else {
                binding.itemAttachmentLinkContainer.visibility = View.VISIBLE
                binding.itemAttachmentLinkContent.text = it
            }
        }

        startforResultGalley = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it != null) {
                scheduleViewModel.setImage(it.data?.data)
            }
//                binding.iv.setImageURI(it.data?.data)
        }

        //Loading courses
        lifecycleScope.launch {
            context?.let {
                courses = MasterMindDatabase(it).getCourseDao().getAllCourses()
                binding.listCourses.adapter = SpinnerAdapter(it, courses!!)
            }
        }

        binding.btnCreateCourse.setOnClickListener {
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToCreateCourseFragment()
            findNavController().navigate(direction)
        }

        binding.fragmentTaskAddAudioAttachment.setOnClickListener {
            val audioDialog = AudioRecordFragment()
            audioDialog.setDataPassListener(this)
            audioDialog.show(requireFragmentManager(), "AudioDialog")
        }

        binding.fragmentTaskAddImageAttachment.setOnClickListener {
            val i = Intent()
            // Activity Action for the intent : Pick an item from the data, returning what was selected.
            i.action = Intent.ACTION_PICK
            i.type = "image/*"
            startforResultGalley.launch(i)
        }

        binding.fragmentTaskAddLinkAttachment.setOnClickListener {
            var txName = EditText(requireContext())
            txName.setHint("Https://")
            AlertDialog.Builder(requireContext())
                .setTitle("Add file attachemnt")
                .setView(txName)
                .setPositiveButton("Add") {view, id ->
                    scheduleViewModel.setFile("https://" + txName.text.toString())
                }
                .setNegativeButton("Cancel") { _, _ -> }.show()
        }

        binding.cancelSchedule.setOnClickListener {
            val direction = CreateScheduleFragmentDirections.actionCreateScheduleFragmentToNavSchedule()
            findNavController().navigate(direction)
        }

        binding.saveSchedule.setOnClickListener {
            if(binding.listCourses.selectedItem == null) {
                Toast.makeText(requireContext(), "Please create new course", Toast.LENGTH_LONG).show()
                return@setOnClickListener;
            }
            if(binding.fragmentTaskTitle.text.isNullOrEmpty()) {
                binding.fragmentTaskTitle.error = "Please enter name"
                return@setOnClickListener;
            }
            lifecycleScope.launch {
                context?.let {
                    val course = binding.listCourses.selectedItem as Course
                    val name = binding.fragmentTaskTitle.text.toString()
                    val desc = binding.fragmentTaskDescription.text.toString()
                    val location = binding.fragmentTaskLocation.text.toString()
                    val date = binding.reminderDate.text.toString()
                    val time = binding.reminderTime.text.toString()
                    val audio = narg.path
                    val image = scheduleViewModel.image.value
                    val file = scheduleViewModel.file.value

                    if(narg.schedule != null) {
                        var _schedule = narg.schedule as Schedule
                        _schedule.course = course.couserName
                        _schedule.name = name
                        _schedule.description = desc
                        _schedule.location = location
                        _schedule.date = scheduleViewModel.date.value.toString()
                        _schedule.time = scheduleViewModel.time.value.toString()
                        _schedule.audio = scheduleViewModel.audio.value
                        _schedule.image = scheduleViewModel.image.value.toString()
                        _schedule.file = scheduleViewModel.file.value

                        MasterMindDatabase(it).getScheduleDao().updateSchedule(_schedule)
                    } else {
                        schedule = Schedule(course.couserName, name, desc, location, date, time, audio, image.toString(), file)
                        MasterMindDatabase(it).getScheduleDao().addSchedule(schedule!!)
                    }

                }
            }
            findNavController().navigate(R.id.action_createScheduleFragment_to_nav_schedule)
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

        binding.itemAttachmentAudio.setOnClickListener{
            AlertDialog.Builder(requireContext())
                .setTitle("Remove file attachemnt")
                .setMessage("Do you want to remove this audio?")
                .setPositiveButton("Yes") {view, id ->
                    scheduleViewModel.removeAudioPath()
                }
                .setNegativeButton("Cancel") { _, _ -> }.show()
        }

        binding.itemAttachmentImage.setOnClickListener{
            AlertDialog.Builder(requireContext())
                .setTitle("Remove file attachemnt")
                .setMessage("Do you want to remove this image?")
                .setPositiveButton("Yes") {view, id ->
                    scheduleViewModel.removeImage()
                }
                .setNegativeButton("Cancel") { _, _ -> }.show()
        }

        binding.itemAttachmentLink.setOnClickListener{
            AlertDialog.Builder(requireContext())
                .setTitle("Remove file attachemnt")
                .setMessage("Do you want to remove this link?")
                .setPositiveButton("Yes") {view, id ->
                    scheduleViewModel.removeLink()
                }
                .setNegativeButton("Cancel") { _, _ -> }.show()
        }

        binding.itemAttachmentAudioPlayPause.setOnClickListener {
            if(isPlaying) {
                isPlaying = false
                binding.itemAttachmentAudioPlayPause.setImageResource(R.drawable.icon_play)
                mediaPlayer?.stop()
            } else {
                binding.itemAttachmentAudioPlayPause.setImageResource(R.drawable.icon_pause)
                isPlaying = true
                mediaPlayer = MediaPlayer()
                // Set the data source of the MediaPlayer
                mediaPlayer.setDataSource(requireContext(), Uri.parse(scheduleViewModel.audio.value.toString()))
                mediaPlayer.prepareAsync()
                mediaPlayer?.setOnPreparedListener {
                    mediaPlayer?.start()
                }
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val course = binding.listCourses.selectedItem as Course
        val name = binding.fragmentTaskTitle.text.toString()
        val desc = binding.fragmentTaskDescription.text.toString()
        val location = binding.fragmentTaskLocation.text.toString()
        val date = binding.reminderDate.text.toString()
        val time = binding.reminderTime.text.toString()
        val audio = narg.path
        val image = scheduleViewModel.image.value
        val file = scheduleViewModel.file.value
        val schedule = Schedule(course.couserName, name, desc, location, date, time, audio, image.toString(), file)
        outState.putSerializable("state_schedule", schedule)
        super.onSaveInstanceState(outState)

    }

    override fun onAudioDialogPositiveClick(data: String) {
        scheduleViewModel.setAudioPath(data)
    }


}