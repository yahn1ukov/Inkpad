package com.ua.inkpad.domain.usecases.notes

import androidx.lifecycle.LiveData
import com.ua.inkpad.data.local.models.entities.NoteEntity
import com.ua.inkpad.data.repositories.NoteRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun execute(title: String): LiveData<List<NoteEntity>> {
        return noteRepository.local.search(title)
    }
}