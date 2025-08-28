package com.example.todolist

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentNewToDoSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.sql.Time
import java.time.LocalTime

class NewToDoSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewToDoSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    private var dueTime: LocalTime? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null)
        {
            binding.taskTitle.text = "Edit ToDo"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.todoDescription.text = editable.newEditable(taskItem!!.desc)
            if(taskItem!!.dueTime != null){
                dueTime = taskItem!!.dueTime!!
                updateTimeButtonText()
            }
        }
        else
        {
            binding.taskTitle.text = "New ToDo"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.AddToDoButton.setOnClickListener {
            saveAction()
        }
        binding.timeSelectButton.setOnClickListener {
            openTimeSelect()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openTimeSelect() {
        if (dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener,dueTime!!.hour, dueTime!!.minute, false)
        dialog.setTitle("Task Due")
        dialog.show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTimeButtonText() {
        binding.timeSelectButton.text= String.format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)
    }

    private fun saveAction(){
        val name = binding.name.text.toString()
        val desc = binding.todoDescription.text.toString()
        if (taskItem == null){
            val newToDo = TaskItem(name,desc, dueTime, null)
            taskViewModel.addTaskItem(newToDo)
        }
        else{
            taskViewModel.updateTaskItem(taskItem!!.id, name, desc, dueTime)
        }
        binding.name.setText("")
        binding.todoDescription.setText("")
        dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewToDoSheetBinding.inflate(inflater,container, false)
        return binding.root
    }

}