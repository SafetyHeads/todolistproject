package com.company.todolistproject.usecases

import com.company.todolistproject.domain.Task
import com.company.todolistproject.repostories.TaskRepository

class SaveTaskImpl(private val taskRepository: TaskRepository) : SaveTask {

    override fun save(name: String) {
        taskRepository.saveTask(Task(name))
//        itemlist.add(name)
//        FileHelper.writeData(itemlist, applicationContext)
    }

}