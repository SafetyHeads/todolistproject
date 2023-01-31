package com.company.todolistproject.repostories

import android.content.Context
import com.company.todolistproject.domain.Task

class TaskRepositoryImpl(context: Context) : TaskRepository {

    private val sharedPref = context.getSharedPreferences("NAME", Context.MODE_PRIVATE)

    override fun saveTask(task: Task) {
        val tasks = requireNotNull(sharedPref.getStringSet("tasks", emptySet()))
        tasks.add(task.name)
        sharedPref.edit().putStringSet("tasks", tasks).commit()
    }

    override fun getTasks(): List<Task> {
        return requireNotNull(sharedPref.getStringSet("tasks", emptySet())).map {
            Task(it)
        }
    }
}