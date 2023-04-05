package com.ua.inkpad.model.utils

import androidx.room.TypeConverter
import com.ua.inkpad.model.enums.Priority

class PropertyConverter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}