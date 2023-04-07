package com.ua.inkpad.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ua.inkpad.data.local.enums.NotePriority
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    @ColumnInfo(name = "priority")
    var notePriority: NotePriority,
    var description: String
) : Parcelable