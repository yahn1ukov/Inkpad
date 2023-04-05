package com.ua.inkpad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ua.inkpad.model.entities.Note
import com.ua.inkpad.model.enums.Priority

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    val emptyDatabase = MutableLiveData(false)

    fun checkIsEmptyDatabase(notes: List<Note>) {
        emptyDatabase.value = notes.isEmpty()
    }

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriorityToPriority(priority: String): Priority {
        return when (priority) {
            "High" -> Priority.HIGH
            "Medium" -> Priority.MEDIUM
            "Low" -> Priority.LOW
            else -> Priority.LOW
        }
    }
}