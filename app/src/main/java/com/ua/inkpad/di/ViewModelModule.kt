package com.ua.inkpad.di

import com.ua.inkpad.data.repositories.NoteRepository
import com.ua.inkpad.domain.usecases.notes.*
import com.ua.inkpad.presentation.notes.viewModels.NoteViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideNoteViewModel(
        getAllUseCase: GetAllUseCase,
        insertUserCase: InsertUserCase,
        updateUseCase: UpdateUseCase,
        deleteUseCase: DeleteUseCase,
        deleteAllUseCase: DeleteAllUseCase,
        searchUseCase: SearchUseCase,
        sortByHighPriorityUseCase: SortByHighPriorityUseCase,
        sortByLowPriorityUseCase: SortByLowPriorityUseCase
    ): NoteViewModel {
        return NoteViewModel(
            getAllUseCase,
            insertUserCase,
            updateUseCase,
            deleteUseCase,
            deleteAllUseCase,
            searchUseCase,
            sortByHighPriorityUseCase,
            sortByLowPriorityUseCase
        )
    }

    @Provides
    fun provideGetAllUseCase(noteRepository: NoteRepository): GetAllUseCase {
        return GetAllUseCase(noteRepository)
    }

    @Provides
    fun provideInsertUseCase(noteRepository: NoteRepository): InsertUserCase {
        return InsertUserCase(noteRepository)
    }

    @Provides
    fun provideUpdateUseCase(noteRepository: NoteRepository): UpdateUseCase {
        return UpdateUseCase(noteRepository)
    }

    @Provides
    fun provideDeleteUseCase(noteRepository: NoteRepository): DeleteUseCase {
        return DeleteUseCase(noteRepository)
    }

    @Provides
    fun provideDeleteAllUseCase(noteRepository: NoteRepository): DeleteAllUseCase {
        return DeleteAllUseCase(noteRepository)
    }

    @Provides
    fun provideSearchUseCase(noteRepository: NoteRepository): SearchUseCase {
        return SearchUseCase(noteRepository)
    }

    @Provides
    fun provideSortByHighPriorityUseCase(noteRepository: NoteRepository): SortByHighPriorityUseCase {
        return SortByHighPriorityUseCase(noteRepository)
    }

    @Provides
    fun sortByLowPriorityUseCase(noteRepository: NoteRepository): SortByLowPriorityUseCase {
        return SortByLowPriorityUseCase(noteRepository)
    }
}