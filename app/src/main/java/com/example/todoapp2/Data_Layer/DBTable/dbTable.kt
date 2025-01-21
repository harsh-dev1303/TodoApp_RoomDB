package com.example.todoapp2.Data_Layer.DBTable

import androidx.compose.runtime.MutableState
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "dbTable")
data class dbTable(
    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    val title:String,
    val date: Long
)
class DateTypeConverter {

    // Convert a Date object to a Long (for database storage)
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    // Convert a Long (from the database) to a Date object
    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }
}
