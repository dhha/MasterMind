package com.example.mastermind.ui.home

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mastermind.ui.model.Schedule

class CreateScheduleViewModel: ViewModel() {
    var course = MutableLiveData<String>()
    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var location = MutableLiveData<String>()
    var date = MutableLiveData<String>()
    var time = MutableLiveData<String>()
    var audio = MutableLiveData<String?>()
    var image = MutableLiveData<Uri?>()
    var file = MutableLiveData<String?>()
    init {
        course.value = ""
        title.value = ""
        description.value = ""
        location.value = ""
        date.value = ""
        time.value = ""
        audio.value = null
        image.value = null
        file.value = null
    }

    fun setAudioPath(path: String?) {
        audio.value = path
    }

    fun setImage(data: Uri?) {
        image.value = data
    }

    fun setFile(path: String?) {
        file.value = path
    }
}