package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class TaskItemDatabase: RoomDatabase() {

    abstract fun taskItemDao(): TaskItemDao

    companion object{
        private var INSTANCE: TaskItemDatabase? = null

        fun getDatabase(context: Context): TaskItemDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, TaskItemDatabase::class.java, "task_item_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}