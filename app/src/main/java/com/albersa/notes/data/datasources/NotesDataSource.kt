package com.albersa.notes.data.datasources

import com.albersa.notes.data.models.Note
import com.albersa.notes.data.room.NotesDatabase
import javax.inject.Inject

class NotesDataSource @Inject constructor(
    private val notesDatabase: NotesDatabase
) {
    suspend fun getAll(): List<Note> {
        return notesDatabase.noteDao().getAll()
    }

    suspend fun saveNote(note: Note) {
        notesDatabase.noteDao().insert(note)
    }
}