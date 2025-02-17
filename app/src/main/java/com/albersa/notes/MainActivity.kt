package com.albersa.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albersa.notes.ui.SaveNote
import com.albersa.notes.ui.UpdateContent
import com.albersa.notes.ui.UpdateTitle
import com.albersa.notes.ui.composables.AddNote
import com.albersa.notes.ui.composables.NotesList
import com.albersa.notes.ui.navigation.AddNote
import com.albersa.notes.ui.navigation.NotesList
import com.albersa.notes.ui.theme.NotesTheme
import com.albersa.notes.ui.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: NotesViewModel = viewModel()
                    val navController = rememberNavController()
                    val notes by viewModel.notes.collectAsState()
                    NavHost(navController = navController, startDestination = NotesList) {
                        composable<NotesList> {
                            LaunchedEffect(Unit) {
                                viewModel.getAllNotes()
                            }
                            NotesList(notes = notes) {
                                navController.navigate(route = AddNote)
                            }
                        }
                        composable<AddNote> {
                            AddNote(title = viewModel.title, content = viewModel.content) {event ->
                                when (event) {
                                    is SaveNote -> {
                                        viewModel.saveNote()
                                        navController.navigateUp()
                                    }
                                    is UpdateContent -> viewModel.updateContent(event.content)
                                    is UpdateTitle -> viewModel.updateTitle(event.title)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
