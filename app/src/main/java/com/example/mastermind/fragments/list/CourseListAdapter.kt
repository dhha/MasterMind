package com.example.mastermind.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R
import com.example.mastermind.model.Course

class CourseListAdapter : RecyclerView.Adapter<CourseListAdapter.MyViewHolder>() {

    private var courses = emptyList<Course>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tvCourseCode : TextView = itemView.findViewById(R.id.tvCourseCode)
        val tvCourseName : TextView = itemView.findViewById(R.id.tvCourseName)
        val rowCourse : View? = itemView.findViewById(R.id.rowCourse)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.course_row,parent,false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentCourse = courses[position]

        holder.tvCourseCode.text = currentCourse.code
        holder.tvCourseName.text = currentCourse.courseName

        holder.rowCourse?.setOnClickListener{
            val action = ListCourseFragmentDirections.actionFromListCourseToListTimer(currentCourse)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount() = courses.size

    fun setData(courses : List<Course>){
        this.courses = courses
        notifyDataSetChanged()
    }
}