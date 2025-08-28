package com.example.todolist

import android.app.Application

class ToDoApplication: Application() {
    private val database by lazy { TaskItemDatabase.getDatabase(this) }
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
}