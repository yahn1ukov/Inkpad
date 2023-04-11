package com.ua.inkpad.presentation.notes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.inkpad.data.local.models.entities.NoteEntity
import com.ua.inkpad.domain.usecases.notes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val getAllUseCase: GetAllUseCase,
    private val insertUserCase: InsertUserCase,
    private val updateUseCase: UpdateUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val deleteAllUseCase: DeleteAllUseCase,
    private val searchNoteUseCase: SearchUseCase,
    private val sortByHighPriorityUseCase: SortByHighPriorityUseCase,
    private val sortByLowPriorityUseCase: SortByLowPriorityUseCase
) : ViewModel() {
    val notes: LiveData<List<NoteEntity>> = getAllUseCase.execute()
    val notesSortedByHighPriority: LiveData<List<NoteEntity>> =
        sortByHighPriorityUseCase.execute()
    val notesSortedByLowPriority: LiveData<List<NoteEntity>> =
        sortByLowPriorityUseCase.execute()

    fun insert(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            insertUserCase.execute(note)
        }
    }

    fun update(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUseCase.execute(note)
        }
    }

    fun delete(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase.execute(note)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllUseCase.execute()
        }
    }

    fun search(title: String): LiveData<List<NoteEntity>> {
        return searchNoteUseCase.execute(title)
    }
}