package com.example.mastermind.ui.Grade

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mastermind.R

class StudentAdapter(var students:MutableList<Student>):RecyclerView.Adapter<StudentAdapter.studentViewHolder>() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentAdapter.studentViewHolder {

    var view =LayoutInflater.from(parent.context).inflate(R.layout.student_layout, parent, false)

        return studentViewHolder(view)

    }

    override fun onBindViewHolder(holder: StudentAdapter.studentViewHolder, position: Int) {
  //  holder.nam.text=books[position].name
        //            holder.auth.text=books[position].author

        holder.full_Name.setText(students[position].fullName);

        holder.course.setText(students[position].course)
        holder.quizScore.setText(students[position].quizScore.toString())
        holder.assignment.setText(students[position].assignmentScore.toString())
        holder.midScore.setText(students[position].midScore.toString())
        holder.finalScore.setText(students[position].finalScore.toString())

    }

    override fun getItemCount(): Int {
       return students.size
    }

inner class studentViewHolder(view: View):RecyclerView.ViewHolder(view){

//val nam:TextView= view.findViewById(R.id.name)
//        val auth:TextView= view.findViewById(R.id.author)

    var full_Name:EditText=view.findViewById<EditText>(R.id.fullName)
    var course:EditText=view.findViewById(R.id.course)
    var quizScore:EditText=view.findViewById(R.id.quizScore)
    var midScore:EditText=view.findViewById(R.id.midScore)
    var assignment:EditText=view.findViewById(R.id.assignment)
    var finalScore:EditText=view.findViewById<EditText>(R.id.finalScore)
    var score:TextView=view.findViewById(R.id.score)

}
}