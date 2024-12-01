package com.albersa.notes.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.albersa.notes.ui.AddNoteEvent
import com.albersa.notes.ui.SaveNote
import com.albersa.notes.ui.UpdateContent
import com.albersa.notes.ui.UpdateTitle

@Composable
fun AddNote(title: String, content: String, onEvent: (AddNoteEvent) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        TextField(value = title,
            label = { Text(text = "Title") },
            onValueChange = {
                onEvent(UpdateTitle(it))
            })
        TextField(value = content,
            singleLine = false,
            minLines = 3,
            label = { Text(text = "Content") },
            onValueChange = {
                onEvent(UpdateContent(it))
            })
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        FloatingActionButton(
            modifier = Modifier
                .size(60.dp, 60.dp)
                .padding(10.dp),
            onClick = {
                onEvent(SaveNote)
            }
        ) {
            Icon(Icons.Filled.Done, "")
        }
    }
}