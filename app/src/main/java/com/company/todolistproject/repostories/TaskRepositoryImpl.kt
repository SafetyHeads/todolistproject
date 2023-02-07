package com.company.todolistproject.repostories

import android.content.Context
import com.company.todolistproject.domain.Task

class TaskRepositoryImpl(private val context: Context) : TaskRepository {

    private val sharedPref
        get() = context.getSharedPreferences("NAME", Context.MODE_PRIVATE)

    override fun saveTask(task: Task) {
        val tasks = requireNotNull(sharedPref.getStringSet("tasks", mutableSetOf())).toMutableSet()
        tasks.add(task.name)
        sharedPref.edit().putStringSet("tasks", tasks).apply()
    }

    override fun getTasks(): List<Task> {
        return requireNotNull(sharedPref.getStringSet("tasks", emptySet())).map {
            Task(it)
        }
    }
}