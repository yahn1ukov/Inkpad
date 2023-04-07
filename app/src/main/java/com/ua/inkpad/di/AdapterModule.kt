package com.ua.inkpad.di

import com.ua.inkpad.presentation.notes.adapters.NoteAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {
    @Provides
    fun provideNoteAdapter(): NoteAdapter {
        return NoteAdapter()
    }
}