package com.example.whattodo

import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    var isChecked: Boolean = false
)
