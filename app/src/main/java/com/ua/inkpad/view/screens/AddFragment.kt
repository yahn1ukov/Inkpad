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
import com.ua.inkpad.R
import com.ua.inkpad.databinding.FragmentAddBinding
import com.ua.inkpad.model.entities.Note
import com.ua.inkpad.viewmodel.NoteViewModel
import com.ua.inkpad.viewmodel.SharedViewModel

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        
        binding.addPrioritiesSpinner.setText("High", false)

        binding.addSubmitBtn.setOnClickListener { insert() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_fragment_menu, menu)
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

        val validation = sharedViewModel.verifyDataFromUser(title, description)

        val parsedPriority = sharedViewModel.parsePriorityToPriority(priority)

        if (validation) {
            val note = Note(0, title, parsedPriority, description)
            noteViewModel.insert(note)
            Toast.makeText(
                requireContext(),
                "Note has been successfully created!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}