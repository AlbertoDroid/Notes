package com.albersa.notes.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.albersa.notes.data.models.Note

@Composable
fun NotesList(notes: List<Note>, onAddNoteClicked: () -> Unit) {
    NotesLazyColumn(
        notes = notes
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        FloatingActionButton(
            modifier = Modifier.size(60.dp, 60.dp),
            onClick = {
                onAddNoteClicked()
            }
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }
}

@Composable
fun NotesLazyColumn(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = { items(notes) {
            NoteCard(note = it) { title ->
                Log.d("TAG_", "Title: ${title}")
            }
        }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(note: Note, onNoteClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = MaterialTheme.shapes.large,
        border = CardDefaults.outlinedCardBorder(true),
        onClick = {
            onNoteClicked(note.title)
        }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = note.title)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = note.content)
        }

    }
}