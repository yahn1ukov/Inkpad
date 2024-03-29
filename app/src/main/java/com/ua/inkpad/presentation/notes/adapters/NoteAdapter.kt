package com.ua.inkpad.presentation.notes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ua.inkpad.data.local.models.entities.NoteEntity
import com.ua.inkpad.databinding.NoteListItemBinding
import com.ua.inkpad.presentation.notes.diff.NoteDiff
import com.ua.inkpad.presentation.notes.screens.NoteListFragmentDirections

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var notes = emptyList<NoteEntity>()
        set(value) {
            val notesDiff = NoteDiff(notes, value)
            val notesDiffResult = DiffUtil.calculateDiff(notesDiff)
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

        fun bind(note: NoteEntity) {
            binding.note = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder.from(parent)

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteUpdateFragment(note)
            it.findNavController().navigate(action)
        }
    }
}