package com.company.todolistproject.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.todolistproject.domain.Task
import com.company.todolistproject.repostories.TaskRepositoryImpl
import com.company.todolistproject.usecases.GetTaskImpl
import com.company.todolistproject.usecases.SaveTaskImpl

class MainViewModel(val context: Context) : ViewModel() {


    private val taskRepository = TaskRepositoryImpl(context)
    private val saveTask = SaveTaskImpl(taskRepository)
    private val getTasks = GetTaskImpl(taskRepository)

    var tasks: List<Task> = getTasks.getList()
    var tasksLiveData: MutableLiveData<List<Task>> = MutableLiveData()

    fun saveTask(name: String) {
        saveTask.save(name)
    }


}