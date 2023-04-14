package com.ua.inkpad.domain.usecases.notes

import androidx.lifecycle.LiveData
import com.ua.inkpad.data.local.models.entities.NoteEntity
import com.ua.inkpad.data.repositories.NoteRepository
import javax.inject.Inject

class SortByLowPriorityUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun execute(): LiveData<List<NoteEntity>> {
        return noteRepository.local.sortByLowPriority()
    }
}