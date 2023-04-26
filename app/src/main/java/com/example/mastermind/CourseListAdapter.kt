package com.example.mastermind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.databinding.CourseListBinding
import com.example.mastermind.ui.model.Course

class CourseListAdapter(private val courseDataSet : List<Course>) : RecyclerView.Adapter<CourseListAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val binding = CourseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentCourse = courseDataSet[position]
        holder.binding.tvCourseName.text = currentCourse.couserName
        holder.binding.tvCourseDescription.text = currentCourse.description
    }

    override fun getItemCount() = courseDataSet.size

    class MyViewHolder(val binding: CourseListBinding) : RecyclerView.ViewHolder(binding.root)

}