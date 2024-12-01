package com.albersa.notes.data.repositories

import com.albersa.notes.data.datasources.NotesDataSource
import com.albersa.notes.data.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDataSource: NotesDataSource
) {
    suspend fun getAllNotes(): List<Note> {
        return withContext(Dispatchers.IO) {
            notesDataSource.getAll()
        }
    }

    suspend fun saveNote(note: Note) {
        withContext(Dispatchers.IO) {
            notesDataSource.saveNote(note)
        }
    }
}