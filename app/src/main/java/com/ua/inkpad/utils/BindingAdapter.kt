package com.ua.inkpad.utils

import android.view.View
import android.widget.AutoCompleteTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.ua.inkpad.R
import com.ua.inkpad.data.local.models.enums.NotePriority
import com.ua.inkpad.utils.Constants.Companion.HIGH_PRIORITY
import com.ua.inkpad.utils.Constants.Companion.LOW_PRIORITY
import com.ua.inkpad.utils.Constants.Companion.MEDIUM_PRIORITY

class BindingAdapter {
    companion object {
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
                NotePriority.HIGH -> view.setText(HIGH_PRIORITY, false)
                NotePriority.MEDIUM -> view.setText(MEDIUM_PRIORITY, false)
                NotePriority.LOW -> view.setText(LOW_PRIORITY, false)
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