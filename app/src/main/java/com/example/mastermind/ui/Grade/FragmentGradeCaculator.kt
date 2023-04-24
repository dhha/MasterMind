package com.example.mastermind.ui.Grade

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mastermind.CreateScheduleActivity
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentGradeCaculatorBinding
import com.example.mastermind.myActivity

class FragmentGradeCaculator : Fragment() {
private lateinit var binding: FragmentGradeCaculatorBinding

private lateinit var lists:ArrayList<Student>

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_grade_caculator, container, false)
     binding= FragmentGradeCaculatorBinding.bind(view)

        binding.activityMy.setOnClickListener {
            val intent = Intent(requireContext(), myActivity::class.java)
            startActivity(intent)
        }

        return binding.root

     /*   lists=ArrayList<Student>()

        var adp=StudentAdapter(lists)*/




       /* lists= ArrayList<Student>()
        lists.add(
            Student(1,"Lemessa Adugna", "Kotlin",
        25,25,25,20.7)
        )
        lists.add(Student(2,"Lemessa Adugna", "Kotlin",
            25,25,25,20))

        binding.re.layoutManager=LinearLayoutManager(this)
        var adp=StudentAdapter(lists)
   binding.re.adapter=adp*/



    }

}