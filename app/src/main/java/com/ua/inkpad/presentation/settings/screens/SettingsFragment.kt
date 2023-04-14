package com.ua.inkpad.presentation.settings.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ua.inkpad.Inkpad
import com.ua.inkpad.R
import com.ua.inkpad.databinding.FragmentSettingsBinding
import com.ua.inkpad.presentation.notes.viewmodels.NoteViewModel
import com.ua.inkpad.presentation.settings.viewmodels.SettingsViewModel
import com.ua.inkpad.utils.Constants.Companion.DARK_THEME_MODE
import com.ua.inkpad.utils.Constants.Companion.LIGHT_THEME_MODE
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_FOLLOW_SYSTEM
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_NO
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_YES
import com.ua.inkpad.utils.Constants.Companion.SYSTEM_THEME_MODE
import com.ua.inkpad.utils.Constants.Companion.THEME_MODES
import javax.inject.Inject

class SettingsFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Inkpad.appComponent.inject(this@SettingsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        setupThemeSpinner()

        binding.databaseBtn.setOnClickListener { confirmDeleteAll() }

        return binding.root
    }

    private fun setupThemeSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            THEME_MODES
        )

        binding.themesSpinner.apply {
            setAdapter(adapter)
            setOnItemClickListener { _, _, position, _ -> setThemeMode(adapter, position) }
        }

        getThemeMode()
    }

    private fun setThemeMode(adapter: ArrayAdapter<String>, position: Int) {
        val selectedThemeMode = adapter.getItem(position)
        when (selectedThemeMode.toString()) {
            SYSTEM_THEME_MODE -> settingsViewModel.setThemeMode(SYSTEM_THEME_MODE)
            LIGHT_THEME_MODE -> settingsViewModel.setThemeMode(LIGHT_THEME_MODE)
            DARK_THEME_MODE -> settingsViewModel.setThemeMode(DARK_THEME_MODE)
        }
        findNavController().navigate(R.id.action_settingsFragment_to_noteListFragment)
    }

    private fun getThemeMode() {
        settingsViewModel.getTheme.observe(viewLifecycleOwner) {
            when (it) {
                MODE_NIGHT_FOLLOW_SYSTEM -> binding.themesSpinner.setText(SYSTEM_THEME_MODE, false)
                MODE_NIGHT_NO -> binding.themesSpinner.setText(LIGHT_THEME_MODE, false)
                MODE_NIGHT_YES -> binding.themesSpinner.setText(DARK_THEME_MODE, false)
            }
        }
    }

    private fun confirmDeleteAll() {
        val builder = MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle("Remove all?")
            setMessage("Are you sure that you want to remove all?")
            setIcon(R.drawable.ic_bin)
            setPositiveButton("Yes") { _, _ -> deleteAll() }
            setNegativeButton("No") { _, _ -> }
        }
        builder.create().show()
    }

    private fun deleteAll() {
        noteViewModel.deleteAll()
        Toast.makeText(
            requireContext(),
            "Notes have been successfully removed!",
            Toast.LENGTH_SHORT
        ).show()
        findNavController().navigate(R.id.action_settingsFragment_to_noteListFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}