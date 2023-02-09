package com.company.todolistproject

import android.app.Application
import com.company.todolistproject.repostories.TaskRepository
import com.company.todolistproject.repostories.TaskRepositoryImpl
import com.company.todolistproject.ui.MainViewModel
import com.company.todolistproject.usecases.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    val appModule = module {
        single<TaskRepository> { TaskRepositoryImpl(get()) }

        single<SaveTask> { SaveTaskImpl(get()) }
        single<GetTasks> { GetTaskImpl(get()) }
        single<DeleteTask> { DeleteTaskImpl(get()) }

        viewModelOf(::MainViewModel)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }

}