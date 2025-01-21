package com.example.todoapp2.UI_Layer.AppViewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp2.Data_Layer.DBTable.dbTable
import com.example.todoapp2.Data_Layer.repository.repo
import com.example.todoapp2.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class appViewmodel @Inject constructor(val Repo:repo) : ViewModel(){
    val todoList=Repo.get_AllData().stateIn(viewModelScope, SharingStarted.WhileSubscribed(),
        emptyList()
    )

    val _state= MutableStateFlow(TodoState())
    val state=combine(_state,todoList){
        _state,todoList->
        _state.copy(
            Todo_List = todoList
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),TodoState())

    fun delete(){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("delete title",state.value.title.value)
            Log.d("delete date", state.value.date.value.time.toString())
           Repo.Delete_data(
               dbTable(
                   id = state.value.id.value,
                   title = state.value.title.value,
                   date= state.value.date.value.time
               ))
        }
    }

    fun take_data(){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("title",state.value.title.toString())
             Repo.Take_data(
                 dbTable(
                     title = state.value.title.value.toString(),
                     date = state.value.date.value.time
                 )
             )
            _state.value.title.value=""
           // _state.value = _state.value.copy(title = mutableStateOf(""))  // Clear the title
        }

    }


}