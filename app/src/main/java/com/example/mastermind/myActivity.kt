package com.example.mastermind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.mastermind.databinding.ActivityMyBinding
import com.example.mastermind.ui.Grade.Grade
import com.example.mastermind.ui.Grade.GradeDatabase

import kotlinx.coroutines.launch



class myActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMyBinding
    private var gradin:Grade?=null
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
        /*
        *     launch {
                /* NoteDatabase needs an context argument, if it is not null
                let perform add or update query
                */
                context?.let {
                    val mNote = Note(noteTitle, noteBody)
                    // note == null means Inserting a new Note
                    if (note == null) {
                        NoteDatabase(it).getNoteDao().addNote(mNote)
                        it.toast("Note Saved")
                    } else {
                        // Update the note
                        mNote.id = note!!.id
                        //   mNote.title = noteTitle
                        NoteDatabase(it).getNoteDao().updateNote(mNote)
                        it.toast("Note Updated")
                    }
                    // Automatically after adding a note need to return to Home_Fragment as per the navigation directions
                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }
            }*/
    /*    lifecycleScope.launch{
            applicationContext?.let {

                var gra = Grade(course,quiz,mid, assignment,final, grade)
                // note == null means Inserting a new Note

                    if(gradin==null) {
                        GradeDatabase(applicationContext).getGradeDao().addGrade(gra)
//                        Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG).show
                    }



                // Automatically after adding a note need to return to Home_Fragment as per the navigation directions

            }

            }*/

        }



    fun resetting(view: View){
        binding.course.setText("")
        binding.quizScore.setText("")
        binding.assignment.setText("")
        binding.midScore.setText("")
        binding.finalScore.setText("")
    }
}




