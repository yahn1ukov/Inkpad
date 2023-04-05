package com.ua.inkpad.view.screens

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ua.inkpad.R
import com.ua.inkpad.databinding.FragmentUpdateBinding
import com.ua.inkpad.model.entities.Note
import com.ua.inkpad.viewmodel.NoteViewModel
import com.ua.inkpad.viewmodel.SharedViewModel

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateFragmentArgs by navArgs()

    private val noteViewMode: NoteViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.args = args

        binding.updateSubmitBtn.setOnClickListener { update() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.update_fragment_menu, menu)
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

        val validation = sharedViewModel.verifyDataFromUser(title, description)

        val parsedPriority = sharedViewModel.parsePriorityToPriority(priority)

        if (validation) {
            val note = Note(
                args.selectedNote.id,
                title,
                parsedPriority,
                description
            )
            noteViewMode.update(note)
            Toast.makeText(
                requireContext(),
                "Note has been successfully updated!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}