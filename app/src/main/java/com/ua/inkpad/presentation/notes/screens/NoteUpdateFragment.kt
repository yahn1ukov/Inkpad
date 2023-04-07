package com.ua.inkpad.presentation.notes.screens

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ua.inkpad.Inkpad
import com.ua.inkpad.R
import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.databinding.FragmentNoteUpdateBinding
import com.ua.inkpad.presentation.notes.viewModels.NoteViewModel
import com.ua.inkpad.utils.DataUtil
import javax.inject.Inject

class NoteUpdateFragment : Fragment() {
    private var _binding: FragmentNoteUpdateBinding? = null
    private val binding get() = _binding!!

    private val args: NoteUpdateFragmentArgs by navArgs()

    @Inject
    lateinit var noteViewMode: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Inkpad.appComponent.inject(this@NoteUpdateFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteUpdateBinding.inflate(inflater, container, false)

        binding.args = args

        binding.updateSubmitBtn.setOnClickListener { update() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.note_update_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete -> confirmDelete()
                    android.R.id.home -> requireActivity().onBackPressed()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun update() {
        val title = binding.updateTitleEditText.text.toString()
        val priority = binding.updatePrioritiesSpinner.text.toString()
        val description = binding.updateDescriptionEditText.text.toString()

        val validation = DataUtil.verifyDataFromUser(title, description)

        val parsedPriority = DataUtil.parsePriorityToPriority(priority)

        if (validation) {
            val noteEntity = NoteEntity(
                args.selectedNote.id,
                title,
                parsedPriority,
                description
            )
            noteViewMode.update(noteEntity)
            Toast.makeText(
                requireContext(),
                "Note has been successfully updated!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_noteUpdateFragment_to_noteListFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmDelete() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Remove '${args.selectedNote.title}'?")
        builder.setMessage("Are you sure that you want to remove '${args.selectedNote.title}'?")
        builder.setIcon(R.drawable.ic_bin)
        builder.setPositiveButton("Yes") { _, _ -> delete() }
        builder.setNegativeButton("No") { _, _ -> }
        builder.create().show()
    }

    private fun delete() {
        noteViewMode.delete(args.selectedNote)
        Toast.makeText(requireContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_noteUpdateFragment_to_noteListFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}