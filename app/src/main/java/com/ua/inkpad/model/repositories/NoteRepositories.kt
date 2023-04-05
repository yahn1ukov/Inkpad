package com.ua.inkpad.model.repositories

import androidx.lifecycle.LiveData
import com.ua.inkpad.model.dao.NoteDao
import com.ua.inkpad.model.entities.Note

class NoteRepositories(private val noteDao: NoteDao) {
    val notes: LiveData<List<Note>> = noteDao.getAll()
    val notesSortedByHighPriority: LiveData<List<Note>> = noteDao.sortByHighPriority()
    val notesSortedByLowPriority: LiveData<List<Note>> = noteDao.sortByLowPriority()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    fun search(title: String): LiveData<List<Note>> {
        return noteDao.search(title)
    }
}