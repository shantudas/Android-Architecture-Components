package com.muvasia.driver.androidarchitecturecomponents.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.muvasia.driver.androidarchitecturecomponents.database.Note
import com.muvasia.driver.androidarchitecturecomponents.database.NoteDatabase
import com.muvasia.driver.androidarchitecturecomponents.repository.NoteRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val noteRepository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteDatabase.getDatabase(application).notedao()
        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.allNotes
    }


    fun insert(note: Note) = scope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}