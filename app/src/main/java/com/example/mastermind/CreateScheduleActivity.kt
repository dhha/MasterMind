package com.example.mastermind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mastermind.databinding.ActivityCreateScheduleBinding

class CreateScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateScheduleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Create new schedule"
    }
}