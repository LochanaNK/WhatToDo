package com.example.whattodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(private val dao: TaskDao): ViewModel(){
    private val tasks = MutableStateFlow<List< TaskItem>>(emptyList())
    val taskItems: StateFlow<List<TaskItem>> = tasks

    init{
        loadTasks()
    }

    private fun loadTasks(){
        viewModelScope.launch(Dispatchers.IO){
            val tasksFromDb = dao.getAllTasks()
            withContext(Dispatchers.Main){
                tasks.value = tasksFromDb

            }
        }
    }

    fun addTask(text:String){
        viewModelScope.launch{
            val newTask = TaskItem(text = text)
            dao.insert(newTask)
            loadTasks()

        }
    }

    fun updateTask(task: TaskItem){
        viewModelScope.launch{
            dao.update(task)
            loadTasks()
        }
    }

    fun deleteTask(task: TaskItem){
        viewModelScope.launch{
            dao.delete(task)
            loadTasks()
        }
    }

}