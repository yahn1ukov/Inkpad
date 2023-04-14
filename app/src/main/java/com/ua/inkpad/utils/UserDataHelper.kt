package com.ua.inkpad.utils

import androidx.lifecycle.MutableLiveData
import com.ua.inkpad.data.local.models.entities.NoteEntity
import com.ua.inkpad.data.local.models.enums.NotePriority
import com.ua.inkpad.utils.Constants.Companion.HIGH_PRIORITY
import com.ua.inkpad.utils.Constants.Companion.MEDIUM_PRIORITY

class UserDataHelper {
    companion object {
        val isEmptyDatabase = MutableLiveData(false)

        fun checkIsEmptyDatabase(notes: List<NoteEntity>) {
            isEmptyDatabase.value = notes.isEmpty()
        }

        fun verifyDataFromUser(title: String, description: String): Boolean {
            return !(title.isEmpty() || description.isEmpty())
        }

        fun parsePriorityToPriority(priority: String): NotePriority {
            return when (priority) {
                HIGH_PRIORITY -> NotePriority.HIGH
                MEDIUM_PRIORITY -> NotePriority.MEDIUM
                else -> NotePriority.LOW
            }
        }
    }
}