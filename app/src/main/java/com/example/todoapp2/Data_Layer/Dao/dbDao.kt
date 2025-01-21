package com.example.todoapp2.Data_Layer.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.todoapp2.Data_Layer.DBTable.dbTable
import com.example.todoapp2.TodoState
import kotlinx.coroutines.flow.Flow

@Dao
interface dbDao{
    @Upsert
    fun take_data(data: dbTable)

    @Delete
    fun delete_data(data: dbTable)

    @Query("Select * from dbTable")
    fun  getAllData(): Flow<List<dbTable>>


}