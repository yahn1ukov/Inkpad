package com.ua.inkpad.data.local.typeconverters

import androidx.room.TypeConverter
import com.ua.inkpad.data.local.enums.NotePriority

class NotePropertyConverter {
    @TypeConverter
    fun fromNotePriority(notePriority: NotePriority): String {
        return notePriority.name
    }

    @TypeConverter
    fun toNotePriority(priority: String): NotePriority {
        return NotePriority.valueOf(priority)
    }
}