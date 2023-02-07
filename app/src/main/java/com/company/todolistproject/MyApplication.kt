package com.company.todolistproject

import android.app.Application
import com.company.todolistproject.repostories.TaskRepository
import com.company.todolistproject.repostories.TaskRepositoryImpl

class MyApplication : Application() {

    lateinit var taskRepository: TaskRepository

    override fun onCreate() {
        super.onCreate()

        taskRepository= TaskRepositoryImpl(applicationContext)
    }
}