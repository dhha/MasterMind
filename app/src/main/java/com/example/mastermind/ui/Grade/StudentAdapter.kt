package com.example.mastermind.ui.Grade

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mastermind.R

class StudentAdapter(var students:List<Grade>):RecyclerView.Adapter<StudentAdapter.studentViewHolder>() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentAdapter.studentViewHolder {

    var view =LayoutInflater.from(parent.context).inflate(R.layout.student_layout, parent, false)

        return studentViewHolder(view)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: StudentAdapter.studentViewHolder, position: Int) {

        holder.course.setText("Course: ${students[position].course}")
        holder.quizScore.setText("Quiz: ${students[position].quizScore.toString()}")
        holder.assignment.setText("Assignment: ${students[position].assignmentScore.toString()}")
        holder.midScore.setText("Mid Exam: ${students[position].midScore.toString()}")
        holder.finalScore.setText("Final Exam: ${students[position].finalScore.toString()}")

  holder.score.text= "Your Final  grade is: ${students[position].calculatedGrade}"

        holder.itemView.setOnClickListener {
   var action= FragmentGradeCaculatorDirections.actionNavScheduleToGradeGragment()
           action.grade=students[position]
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
       return students.size
    }

 class studentViewHolder(view: View):RecyclerView.ViewHolder(view){


    var course:EditText=view.findViewById(R.id.course)
    var quizScore:EditText=view.findViewById(R.id.quizScore)
    var midScore:EditText=view.findViewById(R.id.midScore)
    var assignment:EditText=view.findViewById(R.id.assignment)
    var finalScore:EditText=view.findViewById<EditText>(R.id.finalScore)
    var score:TextView=view.findViewById(R.id.score)

}
}