package com.ua.inkpad.di

import com.ua.inkpad.presentation.MainActivity
import com.ua.inkpad.presentation.notes.screens.NoteAddFragment
import com.ua.inkpad.presentation.notes.screens.NoteListFragment
import com.ua.inkpad.presentation.notes.screens.NoteUpdateFragment
import com.ua.inkpad.presentation.settings.screens.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, RoomModule::class, AdapterModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(noteListFragment: NoteListFragment)
    fun inject(noteAddFragment: NoteAddFragment)
    fun inject(noteUpdateFragment: NoteUpdateFragment)
    fun inject(settingsFragment: SettingsFragment)
}