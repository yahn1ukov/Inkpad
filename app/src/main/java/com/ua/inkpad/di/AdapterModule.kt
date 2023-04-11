package com.ua.inkpad.di

import com.ua.inkpad.presentation.notes.adapters.NoteAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {
    @Provides
    @Singleton
    fun provideNoteAdapter(): NoteAdapter {
        return NoteAdapter()
    }
}