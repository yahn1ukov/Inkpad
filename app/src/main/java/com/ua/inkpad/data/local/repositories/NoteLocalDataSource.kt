package com.ua.inkpad.data.local.repositories

import androidx.lifecycle.LiveData
import com.ua.inkpad.data.local.dao.NoteDao
import com.ua.inkpad.data.local.models.entities.NoteEntity
import javax.inject.Inject

class NoteLocalDataSource @Inject constructor(private val noteDao: NoteDao) {
    fun getAll(): LiveData<List<NoteEntity>> {
        return noteDao.getAll()
    }

    suspend fun insert(note: NoteEntity) {
        noteDao.insert(note)
    }

    suspend fun update(note: NoteEntity) {
        noteDao.update(note)
    }

    suspend fun delete(note: NoteEntity) {
        noteDao.delete(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    fun search(title: String): LiveData<List<NoteEntity>> {
        return noteDao.search(title)
    }

    fun sortByHighPriority(): LiveData<List<NoteEntity>> {
        return noteDao.sortByHighPriority()
    }

    fun sortByLowPriority(): LiveData<List<NoteEntity>> {
        return noteDao.sortByLowPriority()
    }
}