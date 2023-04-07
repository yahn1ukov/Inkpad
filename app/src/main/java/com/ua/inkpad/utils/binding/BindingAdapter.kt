package com.ua.inkpad.utils.binding

import android.view.View
import android.widget.AutoCompleteTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ua.inkpad.R
import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.data.local.enums.NotePriority
import com.ua.inkpad.presentation.notes.screens.NoteListFragmentDirections

class BindingAdapter {
    companion object {
        @BindingAdapter("android:navigateToNoteAddFragment")
        @JvmStatic
        fun navigateToNoteAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController()
                        .navigate(R.id.action_noteListFragment_to_noteAddFragment)
                }
            }
        }

        @BindingAdapter("android:navigateToNoteUpdateFragment")
        @JvmStatic
        fun navigateToNoteUpdateFragment(view: CardView, noteEntity: NoteEntity) {
            view.setOnClickListener {
                val action =
                    NoteListFragmentDirections.actionNoteListFragmentToNoteUpdateFragment(noteEntity)
                view.findNavController().navigate(action)
            }
        }

        @BindingAdapter("android:isEmptyDatabase")
        @JvmStatic
        fun isEmptyDatabase(view: View, isEmptyDatabase: MutableLiveData<Boolean>) {
            if (isEmptyDatabase.value!!) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToString")
        @JvmStatic
        fun parsePriorityToString(view: AutoCompleteTextView, notePriority: NotePriority) {
            when (notePriority) {
                NotePriority.HIGH -> view.setText("High", false)
                NotePriority.MEDIUM -> view.setText("Medium", false)
                NotePriority.LOW -> view.setText("Low", false)
            }
        }

        @BindingAdapter("android:parsePriorityToColor")
        @JvmStatic
        fun parsePriorityToColor(view: CardView, notePriority: NotePriority) {
            when (notePriority) {
                NotePriority.HIGH -> view.setCardBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.red
                    )
                )
                NotePriority.MEDIUM -> view.setCardBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.yellow
                    )
                )
                NotePriority.LOW -> view.setCardBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.green
                    )
                )
            }
        }
    }
}