package com.company.todolistproject.usecases

import com.company.todolistproject.domain.Task

interface GetTasks {
    fun getList(): List<Task>
}