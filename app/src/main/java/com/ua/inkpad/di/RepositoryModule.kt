package com.ua.inkpad.di

import com.ua.inkpad.data.local.repositories.NoteLocalDataSource
import com.ua.inkpad.data.repositories.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideNoteRepository(noteLocalDataSource: NoteLocalDataSource): NoteRepository {
        return NoteRepository(noteLocalDataSource)
    }
}