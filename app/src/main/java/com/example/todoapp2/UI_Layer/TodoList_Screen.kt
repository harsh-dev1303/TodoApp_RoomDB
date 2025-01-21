package com.example.todoapp2.UI_Layer

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp2.Data_Layer.DBTable.dbTable
import com.example.todoapp2.UI_Layer.AppViewmodel.appViewmodel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoList_Screen(viewModel:appViewmodel= hiltViewModel()){
    val todoStates=viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            OutlinedTextField(
                value = todoStates.value.title.value,
                onValueChange = {
                    todoStates.value.title.value=it
                },
                shape = OutlinedTextFieldDefaults.shape,
                singleLine = false,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                maxLines = 1,

                )
            Button(
                onClick = {
                    viewModel.take_data()


                }
            ) {
                Text("Add")
            }
        }
        LazyColumn{
            if (todoStates.value.Todo_List.isEmpty()){
                item {
                    Box(contentAlignment = Alignment.Center){
                        Text(
                            text = "Empty list",
                            color = Color.White,

                            )
                    }

                }
            }
            items(todoStates.value.Todo_List){
               // TodoItem(item,onDelete={})
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)                                    //When padding is applied early in the modifier chain, before modifiers like background, clip, or border, it creates space around the composable (like a margin in traditional layouts).
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(16.dp),                                    //When padding is applied later in the chain, after modifiers like background, clip, or border, it creates space inside the composable's boundary, affecting its children or content.
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(it.date),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(text = it.title.toString(),
                            fontSize = 20.sp,
                            color = Color.White
                        )

                    }

                    IconButton(
                        onClick = {
                            todoStates.value.id.value=it.id
                            todoStates.value.title.value=it.title
                            todoStates.value.date.value= Date(it.date)
                            Log.d("delete title ui",todoStates.value.title.value)
                            Log.d("delete date ui", todoStates.value.date.value.toString())
                            viewModel.delete()

                        }
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        Log.d("delete","called")
                    }

                }
            }
        }
    }

}






