package com.ua.inkpad.domain.usecases.notes

import com.ua.inkpad.data.repositories.NoteRepository
import javax.inject.Inject

class DeleteAllUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend fun execute() {
        noteRepository.local.deleteAll()
    }
}