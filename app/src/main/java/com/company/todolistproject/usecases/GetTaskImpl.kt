package com.company.todolistproject.usecases

import com.company.todolistproject.domain.Task
import com.company.todolistproject.repostories.TaskRepository

class GetTaskImpl(private val repository: TaskRepository) : GetTasks {
    override fun getList(): List<Task> {
        return repository.getTasks()
    }
}