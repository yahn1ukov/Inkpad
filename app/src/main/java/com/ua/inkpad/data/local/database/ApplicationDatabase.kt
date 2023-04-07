package com.ua.inkpad.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ua.inkpad.data.local.dao.NoteDao
import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.data.local.typeconverters.NotePropertyConverter

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(NotePropertyConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}