package com.ua.inkpad.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ua.inkpad.databinding.NoteListItemBinding
import com.ua.inkpad.model.entities.Note
import com.ua.inkpad.view.utils.NoteDiffUtil

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes = emptyList<Note>()
        set(value) {
            val notesDiffUtil = NoteDiffUtil(notes, value)
            val notesDiffResult = DiffUtil.calculateDiff(notesDiffUtil)
            field = value
            notesDiffResult.dispatchUpdatesTo(this)
        }

    class NoteViewHolder(private val binding: NoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                return NoteViewHolder(
                    NoteListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(note: Note) {
            binding.note = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder.from(parent)

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(notes[position])
}