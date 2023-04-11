package com.ua.inkpad.presentation.notes.diff

import androidx.recyclerview.widget.DiffUtil
import com.ua.inkpad.data.local.models.entities.NoteEntity

class NoteDiff(
    private val oldList: List<NoteEntity>,
    private val newList: List<NoteEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].notePriority == newList[newItemPosition].notePriority
}