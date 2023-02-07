package com.company.todolistproject.repostories

import com.company.todolistproject.domain.Task

interface TaskRepository {

    fun saveTask(task: Task)

    fun getTasks(): List<Task>

    fun deleteTask(task: Task)

}