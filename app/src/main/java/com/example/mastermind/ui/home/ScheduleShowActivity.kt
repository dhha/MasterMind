package com.example.mastermind.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mastermind.databinding.ActivityScheduleShowBinding

class ScheduleShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleShowBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}