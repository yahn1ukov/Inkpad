package com.ua.inkpad.domain.usecases.notes

import androidx.lifecycle.LiveData
import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.data.repositories.NoteRepository
import javax.inject.Inject

class SortByHighPriorityUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun execute(): LiveData<List<NoteEntity>> {
        return noteRepository.sortByHighPriority()
    }
}