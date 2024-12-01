package com.albersa.notes.di.modules

import android.app.Application
import androidx.room.Room
import com.albersa.notes.data.datasources.NotesDataSource
import com.albersa.notes.data.repositories.NotesRepository
import com.albersa.notes.data.room.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            NotesDatabase::class.java,
            "notes_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNotesDataSource(database: NotesDatabase): NotesDataSource {
        return NotesDataSource(database)
    }

    @Provides
    @Singleton
    fun provideNotesRepository(dataSource: NotesDataSource): NotesRepository {
        return NotesRepository(dataSource)
    }
}