package com.example.todoapp2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.todoapp2.Data_Layer.DBTable.dbTable
import java.time.Instant
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
data class TodoState @RequiresApi(Build.VERSION_CODES.O) constructor(
    var id:MutableState<Int?> = mutableStateOf(null),
    var Todo_List:List<dbTable> = emptyList(),
    var title:MutableState<String> = mutableStateOf(""),
    var date:MutableState<Date> = mutableStateOf(Date.from(Instant.now()))
)
