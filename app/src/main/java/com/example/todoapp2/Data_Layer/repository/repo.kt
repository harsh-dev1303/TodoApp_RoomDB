package com.example.todoapp2.Data_Layer.repository

import com.example.todoapp2.Data_Layer.DBTable.dbTable
import com.example.todoapp2.Data_Layer.database.dbDatabase
import com.example.todoapp2.TodoState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class repo @Inject constructor(val dbInstance: dbDatabase) {
    suspend fun Take_data(data: dbTable){
         dbInstance.TodoDao().take_data(data)
    }
    suspend fun Delete_data(Data: dbTable){
        dbInstance.TodoDao().delete_data(data = Data)
    }
    fun get_AllData(): Flow<List<dbTable>> {
       return dbInstance.TodoDao().getAllData()
    }
}