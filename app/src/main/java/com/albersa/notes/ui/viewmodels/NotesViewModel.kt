package com.albersa.notes.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albersa.notes.data.models.Note
import com.albersa.notes.data.repositories.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel() {

    private var _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> get() = _notes

    private var currentNoteTitle by mutableStateOf("")
    private var currentNoteContent by mutableStateOf("")

    val title: String get() = currentNoteTitle
    val content: String get() = currentNoteContent

    fun getAllNotes() {
        viewModelScope.launch {
            _notes.value = notesRepository.getAllNotes()
        }
    }

    fun saveNote() {
        viewModelScope.launch {
            notesRepository.saveNote(Note(title = currentNoteTitle, content = currentNoteContent))
            currentNoteContent = String()
            currentNoteTitle = String()
            getAllNotes()
        }
    }

    fun updateTitle(title: String) {
        this.currentNoteTitle = title
    }

    fun updateContent(content: String) {
        this.currentNoteContent = content
    }
}