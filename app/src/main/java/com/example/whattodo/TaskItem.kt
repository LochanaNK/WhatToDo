package com.example.whattodo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TaskItem(
    val text: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)
