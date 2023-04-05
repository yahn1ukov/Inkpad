package com.ua.inkpad.model.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ua.inkpad.model.enums.Priority
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
) : Parcelable