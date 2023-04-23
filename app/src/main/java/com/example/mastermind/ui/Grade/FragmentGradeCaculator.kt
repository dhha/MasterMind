package com.example.mastermind.ui.Grade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentGradeCaculatorBinding

class FragmentGradeCaculator : Fragment() {
private lateinit var binding: FragmentGradeCaculatorBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_grade_caculator, container, false)
     binding= FragmentGradeCaculatorBinding.bind(view)
        return binding.root
    }
}