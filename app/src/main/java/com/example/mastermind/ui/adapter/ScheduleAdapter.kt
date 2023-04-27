package com.example.mastermind.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R
import com.example.mastermind.databinding.ScheduleItemBinding
import com.example.mastermind.ui.home.HomeFragmentDirections
import com.example.mastermind.ui.model.Course
import com.example.mastermind.ui.model.Schedule

class ScheduleAdapter(
    private val schedules: ArrayList<Schedule>,
    private val onScheduleClick: (Schedule) -> Unit,
    private val onScheduleLongClick: (Schedule) -> Unit,

): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val view = ScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view).apply {
            this.itemView.setOnClickListener {
                schedule?.let(onScheduleClick::invoke)
            }
            this.itemView.setOnLongClickListener {
                schedule?.let(onScheduleLongClick::invoke)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val schedule = schedules[position]
        val course = schedule.course
        holder.schedule = schedule
        Log.i("image", schedule.image.isNullOrEmpty().toString())
        with(holder.binding) {
            itemTaskProgrammedOneTimeTitle.text = course.toString() + " - " + schedule.name
            itemTaskProgrammedOneTimeDescription.text = schedule.description
            itemTaskProgrammedOneTimeDate.text = schedule.date
            itemTaskProgrammedOneTimeTime.text = schedule.time
            if(schedule.audio != null) {
                itemTaskProgrammedOneTimeAttachmentAudio.setColorFilter(R.color.icons_enabled)
            }
            if(!schedule.image.toString().equals("null")) {
                itemTaskProgrammedOneTimeAttachmentImage.setColorFilter(R.color.icons_enabled)
            }
            if(schedule.file != null) {
                itemTaskProgrammedOneTimeAttachmentLink.setColorFilter(R.color.icons_enabled)
            }
        }

    }

    override fun getItemCount() = schedules.size

    inner class ViewHolder(val binding: ScheduleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var schedule: Schedule? = null
    }

    fun remove(index: Schedule) {
        schedules.remove(index)
        notifyDataSetChanged()
    }
}