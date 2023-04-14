package com.ua.inkpad.data.repositories

import com.ua.inkpad.data.local.repositories.NoteLocalDataSource
import javax.inject.Inject

class NoteRepository @Inject constructor(noteLocalDataSource: NoteLocalDataSource) {
    val local = noteLocalDataSource
}