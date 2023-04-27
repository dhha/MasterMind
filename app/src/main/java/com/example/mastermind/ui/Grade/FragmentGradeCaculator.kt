package com.example.mastermind.ui.Grade


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room

import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentGradeCaculatorBinding
import com.example.mastermind.myActivity
import com.example.mastermind.ui.model.MasterMindDatabase
import kotlinx.coroutines.launch

class FragmentGradeCaculator : BaseFragment() {
    private lateinit var binding: FragmentGradeCaculatorBinding

    var grade: List<Grade>? = null
    private var grades: List<Grade> = emptyList()


    private lateinit var lists: ArrayList<Grade>

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_grade_caculator, container, false)
        binding = FragmentGradeCaculatorBinding.bind(view)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.course_menu, menu)

        val searchView = menu.findItem(R.id.searchView).actionView as SearchView
        searchView.queryHint = "Search course"

        super.onCreateOptionsMenu(menu, inflater)

    }
    // Filter the text from the List items - Called when the query text is changed by the user.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listOfGrade.setHasFixedSize(true)
        binding.listOfGrade.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        // var adapter=StudentAdapter(grade)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    launch {
                        context?.let {
                            val filteredGrades =
                                MasterMindDatabase(it).getGradeDao().search("%$newText%")
                            (binding.listOfGrade.adapter as? StudentAdapter)?.updateList(
                                filteredGrades
                            )
                        }
                    }
                }
                return false

            }
        })

        launch {
            context?.let {
                var grades = MasterMindDatabase(it).getGradeDao().getAllGrade()
                binding.listOfGrade.adapter = StudentAdapter(grades)
                grade = grades

            }
        }
        binding.activityMy.setOnClickListener {
            val direction = FragmentGradeCaculatorDirections.actionNavScheduleToGradeGragment()
            findNavController().navigate(direction)

        }
    }

}








