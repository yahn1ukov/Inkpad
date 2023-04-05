package com.ua.inkpad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ua.inkpad.model.db.AppDatabase
import com.ua.inkpad.model.entities.Note
import com.ua.inkpad.model.repositories.NoteRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao = AppDatabase.instance(application).noteDao()
    private val noteRepository: NoteRepositories
    val notes: LiveData<List<Note>>
    val notesSortedByHighPriority: LiveData<List<Note>>
    val notesSortedByLowPriority: LiveData<List<Note>>

    init {
        noteRepository = NoteRepositories(noteDao)
        notes = noteRepository.notes
        notesSortedByHighPriority = noteRepository.notesSortedByHighPriority
        notesSortedByLowPriority = noteRepository.notesSortedByLowPriority
    }

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.update(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(note)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAll()
        }
    }

    fun search(title: String): LiveData<List<Note>> {
        return noteRepository.search(title)
    }
}