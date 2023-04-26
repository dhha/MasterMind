package com.example.mastermind

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mastermind.databinding.FragmentGradeBinding
import com.example.mastermind.ui.Grade.BaseFragment
import com.example.mastermind.ui.Grade.Grade
import com.example.mastermind.ui.Grade.GradeDatabase
import com.example.mastermind.ui.Grade.toast
import kotlinx.coroutines.launch


class gradeGragment : BaseFragment() {
private lateinit var binding: FragmentGradeBinding
private var grades:Grade?=null
private val navArgs:gradeGragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=  inflater.inflate(R.layout.fragment_grade, container, false)

        binding= FragmentGradeBinding.bind(view)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        grades=navArgs.grade
        binding.course.setText(grades?.course)
        binding.quizScore.setText(grades?.quizScore)
        binding.assignment.setText(grades?.assignmentScore)
        binding.midScore.setText(grades?.midScore)
        binding.finalScore.setText(grades?.finalScore)
        binding.score.text = grades?.calculatedGrade







        binding.save.setOnClickListener {view->
            var course = binding.course.text.toString()
            var quiz = binding.quizScore.text.toString()
            var assignment = binding.assignment.text.toString()
            var mid = binding.midScore.text.toString()
            var final = binding.finalScore.text.toString()
//            var finalGrade=binding.score.text.toString()
            var totalScore = quiz.toInt() + assignment.toInt() + mid.toInt() + final.toInt()

            val grade = when (totalScore) {

                in 0..59 -> "F"
                in 60..69 -> "D"
                in 70..79 -> "C"
                in 80..89 -> "B"
                in 90..100 -> "A"
                else -> "Invalid score"
            }
            binding.score.text= "The grade for the $course is : $grade"

            if(course.isEmpty()){
                binding.course.error = "course Required"
                binding.course.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
            if(quiz.isEmpty()){
                binding.quizScore.error = "Quiz score Required"
                binding.quizScore.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
            if(assignment.isEmpty()){
                binding.assignment.error = "assignment Required"
                binding.assignment.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
            if(mid.isEmpty()){
                binding.midScore.error = "midScore Required"
                binding.midScore.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
            if(final.isEmpty()){
                binding.finalScore.error = "finalScore Required"
                binding.finalScore.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
           /* //shorter way
            if (!validateInput(course, binding.course, "Course Required")) return@setOnClickListener
            if (!validateInput(quiz, binding.quizScore, "Quiz score Required")) return@setOnClickListener
            if (!validateInput(assignment, binding.assignment, "Assignment Required")) return@setOnClickListener
            if (!validateInput(mid, binding.midScore, "Mid score Required")) return@setOnClickListener
            if (!validateInput(final, binding.finalScore, "Final score Required")) return@setOnClickListener*/

            launch {
/*
*  context?.let {
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
                }*/
           context?.let {
               var grad=Grade(course,quiz,mid,assignment,final,grade)

               if(grades==null){
                   GradeDatabase(it).getGradeDao().addGrade(grad)
//                   Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()
                   it.toast("grade saved")
               }
               else{
                   grad.id=grades!!.id
                   GradeDatabase(it).getGradeDao().updateGrade(grad)
                   it.toast("grade updated")
               }
               val action=gradeGragmentDirections.actionGradeGragmentToNavGrade()
               Navigation.findNavController(view).navigate(action)

           }

            }



        }
        binding.reset.setOnClickListener {
            binding.course.setText("")
            binding.quizScore.setText("")
            binding.assignment.setText("")
            binding.midScore.setText("")
            binding.finalScore.setText("")
        }
        binding.delete.setOnClickListener {
            if (grades != null) deleteGrade() else context?.toast("Cannot Delete")
        }

    }
    private fun deleteGrade() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") {dialog, which ->
                launch {
                    GradeDatabase(context).getGradeDao().deleteGrade(grades!!)
                    //val action = AddNoteFragmentDirections.actionSaveNote()
                    val action=gradeGragmentDirections.actionGradeGragmentToNavGrade()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
            setNegativeButton("No") {dialog, which->
            }
        }.create().show()
    }
    }
fun validateInput(input: String, editText: EditText, errorMessage: String): Boolean {
    if (input.isEmpty()) {
        editText.error = errorMessage
        editText.requestFocus()
        return false
    }
    return true
}
