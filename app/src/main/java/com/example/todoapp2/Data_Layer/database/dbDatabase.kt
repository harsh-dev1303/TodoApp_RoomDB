package com.example.todoapp2.Data_Layer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp2.Data_Layer.DBTable.DateTypeConverter
import com.example.todoapp2.Data_Layer.DBTable.dbTable
import com.example.todoapp2.Data_Layer.Dao.dbDao

@Database(entities = arrayOf(dbTable::class), version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class) // Register the converter
abstract class dbDatabase:RoomDatabase() {
    abstract fun TodoDao():dbDao
}