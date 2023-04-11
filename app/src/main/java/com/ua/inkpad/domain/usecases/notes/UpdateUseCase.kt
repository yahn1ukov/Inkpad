package com.ua.inkpad.domain.usecases.notes

import com.ua.inkpad.data.local.models.entities.NoteEntity
import com.ua.inkpad.data.repositories.NoteRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend fun execute(note: NoteEntity) {
        noteRepository.update(note)
    }
}