package com.example.todolist

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentNewToDoSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewToDoSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewToDoSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null)
        {
            binding.taskTitle.text = "Edit ToDo"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.todoDescription.text = editable.newEditable(taskItem!!.desc)
        }
        else
        {
            binding.taskTitle.text = "New ToDo"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.AddToDoButton.setOnClickListener {
            saveAction()
        }
    }

    private fun saveAction(){
        val name = binding.name.text.toString()
        val desc = binding.todoDescription.text.toString()
        if (taskItem == null){
            val newToDo = TaskItem(name,desc, null, null)
            taskViewModel.addTaskItem(newToDo)
        }
        else{
            taskViewModel.updateTaskItem(taskItem!!.id, name, desc, null)
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