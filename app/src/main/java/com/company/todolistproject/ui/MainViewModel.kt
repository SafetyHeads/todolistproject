package com.company.todolistproject.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.company.todolistproject.MyApplication
import com.company.todolistproject.domain.Task
import com.company.todolistproject.repostories.TaskRepository
import com.company.todolistproject.repostories.TaskRepositoryImpl
import com.company.todolistproject.usecases.GetTaskImpl
import com.company.todolistproject.usecases.SaveTaskImpl

class MainViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    companion object {

        val FactoryKotlinWay: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val taskRepository = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).taskRepository
                MainViewModel(taskRepository = taskRepository)
            }
        }
    }

    private val saveTask = SaveTaskImpl(taskRepository)
    private val getTasks = GetTaskImpl(taskRepository)

    var tasks: List<Task> = getTasks.getList()
    var tasksLiveData: MutableLiveData<List<Task>> = MutableLiveData()

    fun saveTask(name: String) {
        saveTask.save(name)
        tasksLiveData.postValue(getTasks.getList())
    }


}