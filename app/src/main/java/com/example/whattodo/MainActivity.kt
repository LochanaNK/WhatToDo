package com.example.whattodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whattodo.ui.theme.WhatToDoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatToDoTheme {
                ToDoApp()
            }
        }
    }
}

@Composable
fun ToDoApp(){
    var todoText by remember { mutableStateOf("") }
    val todoList = remember { mutableStateListOf<TaskItem>() }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Spacer(modifier = Modifier.height(48.dp))

        Row(verticalAlignment = Alignment.CenterVertically){

            TextField(
                value = todoText,
                onValueChange = { todoText = it },
                label = { Text("Add a Task")},
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if(todoText.isNotBlank()){
                        todoList.add(TaskItem( text= todoText))
                        todoText = ""
                    }
                }
            ){
                Text(
                    style = TextStyle(fontSize = 16.sp),
                    text = "Add"
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn { 
            items(todoList.size){ index ->
                val task = todoList[index]

                ToDoItem(
                    task = task,
                    onCheckedChange = { checked -> task.isChecked.value = checked },
                    onRemove = { todoList.removeAt(index)}
                )
            }
        }
    }

}

@Composable
fun ToDoItem(task: TaskItem,
             onCheckedChange: (Boolean) -> Unit,
             onRemove: () -> Unit){
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Checkbox(
                checked = task.isChecked.value,
                onCheckedChange = { task.isChecked.value = it }
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(text = task.text,
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                style = TextStyle(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhatToDoTheme {
        ToDoApp()
    }
}