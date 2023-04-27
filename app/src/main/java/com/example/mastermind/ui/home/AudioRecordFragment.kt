package com.example.mastermind.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mastermind.R
import com.example.mastermind.databinding.FragmentAudioRecordBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AudioRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AudioRecordFragment : DialogFragment() {
    private lateinit var binding: FragmentAudioRecordBinding
    private lateinit var permissionLauncher : ActivityResultLauncher<Array<String>>
    private var isRecordPermissionGranted = false
    private var isStoragePermissionGranted = false
    var mediaRecorder: MediaRecorder? = null
    var mediaPlayer: MediaPlayer? = null
    var audioFilePath: String? = null
    var isRecording = false
    var isPlaying = false
    private lateinit var listener: audioDialogListener
    fun setDataPassListener(abc: audioDialogListener) {
        listener = abc
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try {
//            listener = context as audioDialogListener
//        } catch (e: ClassCastException) {
//            throw ClassCastException("$context must implement audioDialogListener")
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_audio_record, container, false)
        binding = FragmentAudioRecordBinding.bind(view)

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
            isStoragePermissionGranted= permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE]?:isStoragePermissionGranted
            if(!isStoragePermissionGranted){
                binding.dialogRecordAudioFab.isEnabled = false
                Toast.makeText(requireContext(),
                    "External Storage permission required",
                    Toast.LENGTH_LONG).show()
            }
            isRecordPermissionGranted = permissions[Manifest.permission.RECORD_AUDIO]?:isRecordPermissionGranted
            if(!isRecordPermissionGranted){
                binding.dialogRecordAudioFab.isEnabled = false
                Toast.makeText(requireContext(),
                    "Record permission required",
                    Toast.LENGTH_LONG).show()
            }
        }
        audioSetup()

        binding.dialogRecordAudioFab.setOnClickListener {
            recordAudio(it)
        }

        binding.dialogRecordAudioPlayIcon.setOnClickListener {
            Log.i("Audio path", audioFilePath.toString())
            if(isPlaying == true) {
                stopAudio(it)
            } else {
                playAudio(it)
            }

        }

        binding.dialogRecordAudioRecIcon.setOnClickListener {
            isRecording = false
            binding.dialogRecordAudioPlayIcon.visibility = View.INVISIBLE
            binding.dialogRecordAudioRecIcon.visibility = View.INVISIBLE
            binding.dialogRecordAudioDoneIcon.visibility = View.INVISIBLE
            binding.dialogRecordAudioFab.isEnabled = true
        }

        binding.dialogRecordAudioDoneIcon.setOnClickListener {
            audioFilePath?.let { it1 -> listener.onAudioDialogPositiveClick(it1) }
            dismiss()
        }

        return binding.root
    }

    private fun audioSetup() {
        if (!hasMicrophone()) {
//            binding.stopButton.isEnabled = false
//            binding.playButton.isEnabled = false
//            binding.recordButton.isEnabled = false
            binding.dialogRecordAudioFab.isEnabled = false
        } else {
//            binding.playButton.isEnabled = false
//            binding.stopButton.isEnabled = false
            binding.dialogRecordAudioFab.isEnabled = true
        }
        audioFilePath = requireContext().filesDir
            .absolutePath + "/" + String.hashCode() + ".3gp"
        requestPermissions()
    }
    fun hasMicrophone(): Boolean {
        val pmanager = requireContext().packageManager
        return pmanager?.hasSystemFeature(
            PackageManager.FEATURE_MICROPHONE) ?: false
    }
    private fun requestPermissions(){
        // Logic to check the requested permission is granted or not
        isStoragePermissionGranted = ContextCompat.checkSelfPermission(
            requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
        isRecordPermissionGranted = ContextCompat.checkSelfPermission(
            requireContext(),Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED

        var permissionRequest : MutableList<String> = ArrayList()
        if(!isStoragePermissionGranted){
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!isRecordPermissionGranted){
            permissionRequest.add(Manifest.permission.RECORD_AUDIO)
        }
        // Call the Launcher with the Requested multiple permissions
        if(permissionRequest.isNotEmpty()){
            permissionLauncher.launch(permissionRequest.toTypedArray())
        }
    }
    fun playAudio(view: View) {
//        binding.playButton.isEnabled = false
//        binding.recordButton.isEnabled = false
//        binding.stopButton.isEnabled = true
        isPlaying = true
        binding.dialogRecordAudioPlayIcon.setImageResource(R.drawable.icon_pause)
        mediaPlayer = MediaPlayer()
        // Set the data source of the MediaPlayer
        mediaPlayer?.setDataSource(requireContext(), Uri.parse(audioFilePath))

// Prepare the MediaPlayer asynchronously
        mediaPlayer?.prepareAsync()

// Set a listener to start playing the audio once the MediaPlayer is prepared
        mediaPlayer?.setOnPreparedListener {
            mediaPlayer?.start()
        }
    }
    fun recordAudio(view: View) {
        if(isRecording == true) {
            isRecording = false;
            stopAudio(view)
            binding.dialogRecordAudioFab.setImageResource(R.drawable.icon_audio)
            binding.dialogRecordAudioFab.isEnabled = false

        } else {
            isRecording = true
//        binding.stopButton.isEnabled = true
//        binding.playButton.isEnabled = false
//        binding.recordButton.isEnabled = false
            try {
                /* Deprecated and still can use it, to get support of older version
                the newer version need to pass the context.
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mediaRecorder = MediaRecorder(requireContext())
                }
                else{
                    mediaRecorder = MediaRecorder()
                }
                mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder?.setOutputFormat(
                    MediaRecorder.OutputFormat.THREE_GPP) // 3rd Generation Partnership Project
                mediaRecorder?.setOutputFile(audioFilePath)
                /* Sets the audio encoder to be used for recording.
                 If this method is not called, the output file will not contain an audio track.*/
                mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB) // Adaptive Multi-Rate_NarrowBand
                mediaRecorder?.prepare()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.dialogRecordAudioFab.setImageResource(R.drawable.icon_record)
            mediaRecorder?.start()
        }

    }
    // Stop recording or stop playing audio
    fun stopAudio(view: View) {
        isPlaying = false
//        binding.stopButton.isEnabled = false
//        binding.playButton.isEnabled = true
        binding.dialogRecordAudioPlayIcon.setImageResource(R.drawable.icon_play)
        binding.dialogRecordAudioPlayIcon.visibility = View.VISIBLE
        binding.dialogRecordAudioRecIcon.visibility = View.VISIBLE
        binding.dialogRecordAudioDoneIcon.visibility = View.VISIBLE
//        binding.dialogRecordAudioTime.visibility = View.VISIBLE
        if (isRecording) {
//            binding.recordButton.isEnabled = false
            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder = null
            isRecording = false
        } else {
            mediaPlayer?.release()
            mediaPlayer = null
//            binding.recordButton.isEnabled = true
        }
    }

    interface audioDialogListener {
        fun onAudioDialogPositiveClick(data: String)
    }
}