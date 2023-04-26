package com.example.mastermind.ui.home

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mastermind.ui.model.Course


class SpinnerAdapter(context: Context, courses: List<Course>)
    : ArrayAdapter<Course>(context, 0, courses) {

    // MARK: - Override methods

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    // MARK: - Private methods
    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val course = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(
            com.example.mastermind.R.layout.item_spinner,
            parent,
            false
        )
        val textViewName: TextView = view!!.findViewById(com.example.mastermind.R.id.courseName)
        textViewName?.text = course?.couserName.toString()
        return view
    }
}