package com.ua.inkpad.data.repositories

import androidx.lifecycle.LiveData
import com.ua.inkpad.data.local.models.entities.NoteEntity
import com.ua.inkpad.data.local.repositories.NoteLocalDataSource
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteLocalDataSource: NoteLocalDataSource) {
    fun getAll(): LiveData<List<NoteEntity>> {
        return noteLocalDataSource.getAll()
    }

    suspend fun insert(note: NoteEntity) {
        noteLocalDataSource.insert(note)
    }

    suspend fun update(note: NoteEntity) {
        noteLocalDataSource.update(note)
    }

    suspend fun delete(note: NoteEntity) {
        noteLocalDataSource.delete(note)
    }

    suspend fun deleteAll() {
        noteLocalDataSource.deleteAll()
    }

    fun search(title: String): LiveData<List<NoteEntity>> {
        return noteLocalDataSource.search(title)
    }

    fun sortByHighPriority(): LiveData<List<NoteEntity>> {
        return noteLocalDataSource.sortByHighPriority()
    }

    fun sortByLowPriority(): LiveData<List<NoteEntity>> {
        return noteLocalDataSource.sortByLowPriority()
    }
}