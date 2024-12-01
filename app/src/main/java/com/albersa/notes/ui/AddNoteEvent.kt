package com.albersa.notes.ui

sealed class AddNoteEvent
data object SaveNote: AddNoteEvent()
data class  UpdateTitle(val title: String): AddNoteEvent()
data class  UpdateContent(val content: String): AddNoteEvent()

