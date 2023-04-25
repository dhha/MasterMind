package com.example.mastermind.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentAudioRecordBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AudioRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AudioRecordFragment : Fragment() {
    private lateinit var binding: FragmentAudioRecordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_audio_record, container, false)
        binding = FragmentAudioRecordBinding.bind(view)
        return binding.root
    }

}