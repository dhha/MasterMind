package com.example.mastermind.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R
import com.example.mastermind.databinding.ScheduleItemBinding
import com.example.mastermind.ui.model.Course
import com.example.mastermind.ui.model.Schedule

class ScheduleAdapter(
    private val schedules: List<Schedule>

): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val view = ScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val schedule = schedules[position]
        val course = schedule.course
        holder.schedule = schedule
        with(holder.binding) {
            itemTaskProgrammedOneTimeTitle.text = course.toString() + " - " + schedule.name
            itemTaskProgrammedOneTimeDescription.text = schedule.description
            itemTaskProgrammedOneTimeDate.text = schedule.date
            itemTaskProgrammedOneTimeTime.text = schedule.time
            if(schedule.audio != null) {
                itemTaskProgrammedOneTimeAttachmentAudio.setColorFilter(R.color.icons_enabled)
            }
            if(schedule.image != null) {
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
}