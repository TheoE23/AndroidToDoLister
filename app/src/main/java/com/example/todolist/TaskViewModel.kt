package com.example.todolist

import android.R
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {
    var name = MutableLiveData<String>()
    var desc = MutableLiveData<String>()
}