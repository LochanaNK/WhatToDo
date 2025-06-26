package com.example.whattodo

import androidx.room.Dao
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<TaskItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskItem)

    @Delete
    suspend fun delete(task: TaskItem)

    @Update
    suspend fun update(task: TaskItem)

    @Query("SELECT * FROM tasks WHERE isChecked = 'true'")
    fun getCompletedTasks(): Flow<List<TaskItem>>

}