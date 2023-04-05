package com.ua.inkpad.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ua.inkpad.model.dao.NoteDao
import com.ua.inkpad.model.entities.Note
import com.ua.inkpad.model.utils.PropertyConverter

@Database(entities = [Note::class], version = 1)
@TypeConverters(PropertyConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun instance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "Inkpad.db"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun noteDao(): NoteDao
}