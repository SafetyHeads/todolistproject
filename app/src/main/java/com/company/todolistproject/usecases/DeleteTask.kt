package com.company.todolistproject.usecases

import com.company.todolistproject.domain.Task

interface DeleteTask {

    fun delete(task: Task?, callDeleteCallback: DeleteTaskImpl.DeleteCallback)
}