package com.albersa.notes

import com.albersa.notes.data.repositories.NotesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@HiltAndroidTest
class NotesListTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockNotesRepository: NotesRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun getAllNotesTest() {
        runBlocking {
            Mockito.`when`(mockNotesRepository.getAllNotes()).thenReturn(emptyList())
            val list = mockNotesRepository.getAllNotes()
            assert(!list.isEmpty())
        }
    }
}