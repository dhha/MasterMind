package com.example.mastermind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mastermind.databinding.ActivityMyBinding

class myActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="my activity"


      }

    fun saving(view: View){
        var course=binding.course.text.toString()
        var quiz=binding.quizScore.text.toString()
        var assignment=binding.assignment.text.toString()
        var mid=binding.midScore.text.toString()
        var final=binding.finalScore.text.toString()
        var totalScore = quiz.toInt()+assignment.toInt()+mid.toInt()+final.toInt()

          val grade = when (totalScore) {

              in 0..59 -> "F"
              in 60..69 -> "D"
              in 70..79 -> "C"
              in 80..89 -> "B"
              in 90..100 -> "A"
              else -> "Invalid score"
          }

        binding.score.text= "The grade for the $course is : $grade"

    }
    fun resetting(view: View){
        binding.course.setText("")
        binding.quizScore.setText("")
        binding.assignment.setText("")
        binding.midScore.setText("")
        binding.finalScore.setText("")
    }
}


