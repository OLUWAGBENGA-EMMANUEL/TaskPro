package uk.ac.tees.scedt.mad.d3740020.taskpro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uk.ac.tees.scedt.mad.d3740020.taskpro.util.TodoItem

@Composable
fun TodoScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    var todoList by remember { mutableStateOf(listOf<TodoItem>()) }
    var newTodo by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                if (newTodo.text.isEmpty()) {
                    Text("Enter task...", color = androidx.compose.ui.graphics.Color.Gray)
                }
                BasicTextField(
                    value = newTodo,
                    onValueChange = { newTodo = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(onClick = {
                if (newTodo.text.isNotBlank()) {
                    todoList = todoList + TodoItem(newTodo.text)
                    newTodo = TextFieldValue("")
                }
            }) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TodoList(todoList, onToggleComplete = { index ->
            todoList = todoList.mapIndexed { i, item ->
                if (i == index) item.copy(completed = !item.completed) else item
            }
        }, onDelete = { index ->
            todoList = todoList.filterIndexed { i, _ -> i != index }
        }, onEdit = { index, newText ->
            todoList = todoList.mapIndexed { i, item ->
                if (i == index) item.copy(text = newText, isEditing = false) else item
            }
        }, onToggleEdit = { index ->
            todoList = todoList.mapIndexed { i, item ->
                if (i == index) item.copy(isEditing = !item.isEditing) else item
            }
        })
    }
}

@Composable
fun TodoList(
    items: List<TodoItem>,
    onToggleComplete: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (Int, String) -> Unit,
    onToggleEdit: (Int) -> Unit
) {
    LazyColumn {
        itemsIndexed(items) { index, item ->
            Row(modifier = Modifier.fillMaxWidth().padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = item.completed,
                    onCheckedChange = { onToggleComplete(index) }
                )
                if (item.isEditing) {
                    var editText by remember { mutableStateOf(TextFieldValue(item.text)) }
                    BasicTextField(
                        value = editText,
                        onValueChange = { editText = it },
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    )
                    Button(onClick = { onEdit(index, editText.text) }) {
                        Text("Save")
                    }
                } else {
                    Text(
                        text = item.text,
                        modifier = Modifier.weight(1f).padding(start = 8.dp),
                        style = if (item.completed) androidx.compose.ui.text.TextStyle(textDecoration = TextDecoration.LineThrough) else androidx.compose.ui.text.TextStyle()
                    )
                    IconButton(onClick = { onToggleEdit(index) }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
                    }
                }
                IconButton(onClick = { onDelete(index) }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

