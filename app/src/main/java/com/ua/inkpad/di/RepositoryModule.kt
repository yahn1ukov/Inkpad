package com.ua.inkpad.di

import com.ua.inkpad.data.local.repositories.NoteLocalDataSource
import com.ua.inkpad.data.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideNoteRepository(noteLocalDataSource: NoteLocalDataSource): NoteRepository {
        return NoteRepository(noteLocalDataSource)
    }
}