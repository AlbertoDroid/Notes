package com.albersa.notes.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albersa.notes.data.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id = (:noteId)")
    suspend fun findById(noteId: Long): Note

    @Update
    suspend fun update(note: Note)

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)
}