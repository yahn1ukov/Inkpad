package com.ua.inkpad.view.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ua.inkpad.R
import com.ua.inkpad.databinding.FragmentListBinding
import com.ua.inkpad.model.entities.Note
import com.ua.inkpad.view.adapters.NoteAdapter
import com.ua.inkpad.view.extenstions.SwipeDelete
import com.ua.inkpad.view.utils.hideKeyboard
import com.ua.inkpad.view.utils.observeOnce
import com.ua.inkpad.viewmodel.NoteViewModel
import com.ua.inkpad.viewmodel.SharedViewModel

class ListFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    private val noteAdapter: NoteAdapter by lazy { NoteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        setupRecyclerView()

        loadNotes()

        binding.lifecycleOwner = this
        binding.sharedViewMode = sharedViewModel

        hideKeyboard(requireActivity())

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = noteAdapter
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        swipeDelete(binding.recyclerView)
    }

    private fun swipeDelete(recyclerView: RecyclerView) {
        val swipeDeleteCallback = object : SwipeDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = noteAdapter.notes[viewHolder.adapterPosition]
                noteViewModel.delete(note)
                noteAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeleted(viewHolder.itemView, note)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeleted(view: View, deletedNote: Note) {
        val snackBar = Snackbar.make(view, "Removed '${deletedNote.title}'", Snackbar.LENGTH_LONG)
        snackBar.setAction("Undo") {
            noteViewModel.insert(deletedNote)
        }
        snackBar.show()
    }

    private fun loadNotes() {
        noteViewModel.notes.observe(viewLifecycleOwner) {
            sharedViewModel.checkIsEmptyDatabase(it)
            noteAdapter.notes = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.list_fragment_menu, menu)

                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as SearchView
                searchView.apply {
                    isSubmitButtonEnabled = true
                    setOnQueryTextListener(this@ListFragment)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_settings -> findNavController().navigate(R.id.action_listFragment_to_settingsFragment)
                    R.id.menu_priority_high -> loadNotesByHighPriority()
                    R.id.menu_priority_low -> loadNotesByLowPriority()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { searchNote(query) }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let { searchNote(query) }
        return true
    }

    private fun searchNote(query: String) {
        val title = "%$query%"
        noteViewModel.search(title).observeOnce(viewLifecycleOwner) {
            it?.let { noteAdapter.notes = it }
        }
    }

    private fun loadNotesByHighPriority() {
        noteViewModel.notesSortedByHighPriority.observe(viewLifecycleOwner) {
            noteAdapter.notes = it
        }
    }

    private fun loadNotesByLowPriority() {
        noteViewModel.notesSortedByLowPriority.observe(viewLifecycleOwner) {
            noteAdapter.notes = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}