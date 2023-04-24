package com.example.mastermind.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.databinding.ScheduleItemBinding
import com.example.mastermind.ui.model.Schedule

class ScheduleAdapter(
    private val schedules: List<Schedule>

): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val schedule = schedules[position]
        holder.schedule = schedule
        with(schedule) {

        }
    }

    override fun getItemCount() = schedules.size

    inner class ViewHolder(val binding: ScheduleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var schedule: Schedule? = null
    }
}