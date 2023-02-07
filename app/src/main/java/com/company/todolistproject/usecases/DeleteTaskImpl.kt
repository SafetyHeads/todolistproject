package com.company.todolistproject.usecases

import android.util.Log
import com.company.todolistproject.domain.Task
import com.company.todolistproject.repostories.TaskRepository

class DeleteTaskImpl(private val taskRepository: TaskRepository) : DeleteTask {

    interface DeleteCallback {
        fun onSuccess()
        fun onItemNotExist()
        fun invalidParameters()
    }

    override fun delete(task: Task?, callDeleteCallback: DeleteCallback) {
        task?.let {
            taskRepository.deleteTask(task)
            callDeleteCallback.onSuccess()
        } ?: run {
            Log.d("DeleteTaskImpl", "Task object is null")
            callDeleteCallback.onItemNotExist()
        }
    }
}