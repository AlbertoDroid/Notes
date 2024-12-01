package com.albersa.notes.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.albersa.notes.data.models.Note
import com.albersa.notes.data.room.dao.NoteDao

@Database(entities = [Note::class], version = 2)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}