package com.company.todolistproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.todolistproject.domain.Task
import com.company.todolistproject.usecases.*

class MainViewModel(private val saveTask: SaveTask, private val getTasks: GetTasks, private val deleteTask: DeleteTask) : ViewModel() {


    var tasks: List<Task> = getTasks.getList()
    var tasksLiveData: MutableLiveData<List<Task>> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun saveTask(name: String) {
        saveTask.save(name)
        tasksLiveData.postValue(getTasks.getList())
    }

    fun deleteTask(task: Task?) {
        deleteTask.delete(task, object : DeleteTaskImpl.DeleteCallback {
            override fun onSuccess() {
                tasksLiveData.postValue(getTasks.getList())
            }

            override fun onItemNotExist() {
                errorLiveData.postValue("Blad")
            }

            override fun invalidParameters() {

            }
        })

    }

}