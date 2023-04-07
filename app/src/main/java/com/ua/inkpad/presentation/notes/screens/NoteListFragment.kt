package com.ua.inkpad.presentation.notes.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ua.inkpad.Inkpad
import com.ua.inkpad.R
import com.ua.inkpad.data.local.entities.NoteEntity
import com.ua.inkpad.databinding.FragmentNoteListBinding
import com.ua.inkpad.presentation.notes.adapters.NoteAdapter
import com.ua.inkpad.presentation.notes.handlers.SwipeToDeleteNoteHandler
import com.ua.inkpad.presentation.notes.viewModels.NoteViewModel
import com.ua.inkpad.utils.DataUtil
import com.ua.inkpad.utils.extensions.observeOnce
import com.ua.inkpad.utils.helpers.hideKeyboard
import javax.inject.Inject

class NoteListFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Inkpad.appComponent.inject(this@NoteListFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()

        loadNotes()

        binding.dataUtil = DataUtil

        hideKeyboard(requireActivity())

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = noteAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = DefaultItemAnimator()
        }

        swipeToDeleteNote(binding.recyclerView)
    }

    private fun swipeToDeleteNote(recyclerView: RecyclerView) {
        val swipeToDeleteNoteCallback = object : SwipeToDeleteNoteHandler() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = noteAdapter.notes[viewHolder.adapterPosition]
                noteViewModel.delete(note)
                noteAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedNote(viewHolder.itemView, note)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteNoteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedNote(view: View, deletedNote: NoteEntity) {
        val snackBar =
            Snackbar.make(view, "Removed '${deletedNote.title}'", Snackbar.LENGTH_LONG)
        snackBar.setAction("Undo") { noteViewModel.insert(deletedNote) }
        snackBar.show()
    }

    private fun loadNotes() {
        noteViewModel.notes.observe(viewLifecycleOwner) {
            DataUtil.checkIsEmptyDatabase(it)
            noteAdapter.notes = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.note_list_fragment_menu, menu)

                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as SearchView
                searchView.apply {
                    isSubmitButtonEnabled = true
                    setOnQueryTextListener(this@NoteListFragment)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_settings -> findNavController().navigate(R.id.action_noteListFragment_to_settingsFragment)
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