package com.ua.inkpad.utils

import androidx.lifecycle.MutableLiveData
import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.data.local.enums.NotePriority

class DataUtil {
    companion object {
        val isEmptyDatabase = MutableLiveData(false)

        fun checkIsEmptyDatabase(noteEntities: List<NoteEntity>) {
            isEmptyDatabase.value = noteEntities.isEmpty()
        }

        fun verifyDataFromUser(title: String, description: String): Boolean {
            return !(title.isEmpty() || description.isEmpty())
        }

        fun parsePriorityToPriority(priority: String): NotePriority {
            return when (priority) {
                "High" -> NotePriority.HIGH
                "Medium" -> NotePriority.MEDIUM
                "Low" -> NotePriority.LOW
                else -> NotePriority.LOW
            }
        }
    }
}