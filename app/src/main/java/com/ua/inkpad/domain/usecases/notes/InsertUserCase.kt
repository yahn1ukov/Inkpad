package com.ua.inkpad.domain.usecases.notes

import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.data.repositories.NoteRepository
import javax.inject.Inject

class InsertUserCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend fun execute(note: NoteEntity) {
        noteRepository.insert(note)
    }
}