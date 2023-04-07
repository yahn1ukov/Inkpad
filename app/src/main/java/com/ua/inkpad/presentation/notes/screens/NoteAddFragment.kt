package com.ua.inkpad.presentation.notes.screens

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.ua.inkpad.Inkpad
import com.ua.inkpad.R
import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.databinding.FragmentNoteAddBinding
import com.ua.inkpad.presentation.notes.viewModels.NoteViewModel
import com.ua.inkpad.utils.DataUtil
import javax.inject.Inject

class NoteAddFragment : Fragment() {
    private var _binding: FragmentNoteAddBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Inkpad.appComponent.inject(this@NoteAddFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteAddBinding.inflate(inflater, container, false)

        binding.addPrioritiesSpinner.setText("High", false)
        binding.addSubmitBtn.setOnClickListener { insert() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.note_add_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    android.R.id.home -> requireActivity().onBackPressed()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun insert() {
        val title = binding.addTitleEditText.text.toString()
        val priority = binding.addPrioritiesSpinner.text.toString()
        val description = binding.addDescriptionEditText.text.toString()

        val validation = DataUtil.verifyDataFromUser(title, description)

        val parsedPriority = DataUtil.parsePriorityToPriority(priority)

        if (validation) {
            val noteEntity = NoteEntity(0, title, parsedPriority, description)
            noteViewModel.insert(noteEntity)
            Toast.makeText(
                requireContext(),
                "Note has been successfully created!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_noteAddFragment_to_noteListFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}