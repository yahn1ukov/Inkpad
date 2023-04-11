package com.ua.inkpad.di

import com.ua.inkpad.data.repositories.NoteRepository
import com.ua.inkpad.domain.usecases.notes.*
import com.ua.inkpad.presentation.notes.viewmodels.NoteViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
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
    @Singleton
    fun provideGetAllUseCase(noteRepository: NoteRepository): GetAllUseCase {
        return GetAllUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideInsertUseCase(noteRepository: NoteRepository): InsertUserCase {
        return InsertUserCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateUseCase(noteRepository: NoteRepository): UpdateUseCase {
        return UpdateUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteUseCase(noteRepository: NoteRepository): DeleteUseCase {
        return DeleteUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteAllUseCase(noteRepository: NoteRepository): DeleteAllUseCase {
        return DeleteAllUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(noteRepository: NoteRepository): SearchUseCase {
        return SearchUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideSortByHighPriorityUseCase(noteRepository: NoteRepository): SortByHighPriorityUseCase {
        return SortByHighPriorityUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun sortByLowPriorityUseCase(noteRepository: NoteRepository): SortByLowPriorityUseCase {
        return SortByLowPriorityUseCase(noteRepository)
    }
}