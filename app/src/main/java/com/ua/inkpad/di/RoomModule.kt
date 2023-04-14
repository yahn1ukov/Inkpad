package com.ua.inkpad.di

import android.content.Context
import androidx.room.Room
import com.ua.inkpad.data.local.dao.NoteDao
import com.ua.inkpad.data.local.database.ApplicationDatabase
import com.ua.inkpad.data.local.repositories.NoteLocalDataSource
import com.ua.inkpad.utils.Constants.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideApplicationDatabase(context: Context): ApplicationDatabase {
        return Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(applicationDatabase: ApplicationDatabase): NoteDao {
        return applicationDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideNoteLocalDataSource(noteDao: NoteDao): NoteLocalDataSource {
        return NoteLocalDataSource(noteDao)
    }
}