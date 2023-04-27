package com.example.mastermind

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mastermind.databinding.FragmentGradeBinding
import com.example.mastermind.ui.Grade.BaseFragment
import com.example.mastermind.ui.Grade.Grade
import com.example.mastermind.ui.Grade.toast
import com.example.mastermind.ui.model.MasterMindDatabase
import kotlinx.coroutines.launch


class gradeGragment : BaseFragment() {
private lateinit var binding: FragmentGradeBinding
private var grades:Grade?=null
    private lateinit var values: Array<String>
private val navArgs:gradeGragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=  inflater.inflate(R.layout.fragment_grade, container, false)
        binding= FragmentGradeBinding.bind(view)
        // Inflate the layout for this fragment

        return binding.root

    }
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        values = resources.getStringArray(R.array.courses)

        var adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, values)
     binding.courseSpinner.adapter=adapter
        values = resources.getStringArray(R.array.courses)

        binding.courseSpinner.adapter = adapter
        grades=navArgs.grade
        binding.courseSpinner.setSelection(values.indexOf(grades?.course))
        binding.quizScore.setText(grades?.quizScore)
        binding.assignment.setText(grades?.assignmentScore)
        binding.midScore.setText(grades?.midScore)
        binding.finalScore.setText(grades?.finalScore)
        binding.score.text = grades?.calculatedGrade

        binding.save.setOnClickListener {view->
            //var spinner = binding.course.text.toString()
            var spinner = binding.courseSpinner.selectedItem.toString()
            var quiz = binding.quizScore.text.toString()
            var assignment = binding.assignment.text.toString()
            var mid = binding.midScore.text.toString()
            var final = binding.finalScore.text.toString()


            /*if(spinner.isEmpty()){
                // binding.courseSpinner.error = "course Required"
                Toast.makeText(requireContext(), "Course Required", Toast.LENGTH_SHORT).show()
                binding.courseSpinner.requestFocus()
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
            }*/

             //shorter way
           if (!validateInput(quiz, binding.quizScore, "Quiz score Required")) return@setOnClickListener
           if (!validateInput(assignment, binding.assignment, "Assignment Required")) return@setOnClickListener
           if (!validateInput(mid, binding.midScore, "Mid score Required")) return@setOnClickListener
           if (!validateInput(final, binding.finalScore, "Final score Required")) return@setOnClickListener
            var totalScore = ((quiz.toInt())*0.2 + (assignment.toInt())*0.1 +( mid.toInt())*0.3 + (final.toInt())*0.4)
            val grade = when (totalScore) {

                in 0.01..59.99 -> "F"
                in 60.01..69.99 -> "D"
                in 70.01..79.99 -> "C"
                in 80.01..89.99 -> "B"
                in 90.01..100.0 -> "A"
                else -> "Invalid score"
            }
            binding.score.text= "The grade for the $spinner is : $grade"




            launch {

           context?.let {
               var grad=Grade(spinner,quiz,mid,assignment,final,grade)

               if(grades==null){
                   MasterMindDatabase(it).getGradeDao().addGrade(grad)
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()
                   it.toast("grade saved")
               }
               else{
                   grad.id=grades!!.id
                   MasterMindDatabase(it).getGradeDao().updateGrade(grad)
                   it.toast("grade updated")
               }
               val action=gradeGragmentDirections.actionGradeGragmentToNavGrade()
               Navigation.findNavController(view).navigate(action)

           }

            }
        }
        binding.reset.setOnClickListener {
           // binding.courseSpinner.setText("")
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
                    MasterMindDatabase(context).getGradeDao().deleteGrade(grades!!)
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
