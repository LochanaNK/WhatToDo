package com.example.whattodo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskItem::class],version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?:synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "wattodo_db"
                ).build().also{
                    INSTANCE = it
                }
            }
        }
    }

}