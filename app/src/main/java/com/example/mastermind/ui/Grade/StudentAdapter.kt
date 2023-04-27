package com.example.mastermind.ui.Grade

import android.annotation.SuppressLint
import android.view.*
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R

import com.example.mastermind.ui.model.MasterMindDatabase

class StudentAdapter(private var students: List<Grade>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private lateinit var database: MasterMindDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.course.text = "Course: ${student.course}"
        holder.quizScore.text = "Quiz: ${student.quizScore}"
        holder.assignment.text = "Assignment: ${student.assignmentScore}"
        holder.midScore.text = "Mid Exam: ${student.midScore}"
        holder.finalScore.text = "Final Exam: ${student.finalScore}"
        holder.score.text = "Your Final grade is: ${student.calculatedGrade}"

        holder.editButton.setOnClickListener {
            val action = FragmentGradeCaculatorDirections.actionNavScheduleToGradeGragment()
            action.grade = student
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val course: TextView = view.findViewById(R.id.course)
        val quizScore: TextView = view.findViewById(R.id.quizScore)
        val midScore: TextView = view.findViewById(R.id.midScore)
        val assignment: TextView = view.findViewById(R.id.assignment)
        val finalScore: TextView = view.findViewById(R.id.finalScore)
        val score: TextView = view.findViewById(R.id.score)
        val editButton: ImageButton = view.findViewById(R.id.edit)
    }

    fun updateList(newList: List<Grade>) {
        students = newList
        notifyDataSetChanged()
    }
}



