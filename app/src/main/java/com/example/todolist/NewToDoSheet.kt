package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentNewToDoSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewToDoSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewToDoSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.AddToDoButton.setOnClickListener {
            saveAction()
        }
    }

    private fun saveAction(){
        taskViewModel.name.value = binding.name.text.toString()
        taskViewModel.desc.value = binding.todoDescription.text.toString()
        binding.name.setText("")
        binding.todoDescription.setText("")
        dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewToDoSheetBinding.inflate(inflater,container, false)
        return binding.root
    }

}