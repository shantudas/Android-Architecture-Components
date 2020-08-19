package com.muvasia.driver.androidarchitecturecomponents.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.muvasia.driver.androidarchitecturecomponents.database.Note
import com.muvasia.driver.androidarchitecturecomponents.database.NoteDatabase
import com.muvasia.driver.androidarchitecturecomponents.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


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

    fun getNoteItem(itemId: Int?): LiveData<Note> {
        return noteRepository.getNoteItem(itemId)
    }

    fun insert(note: Note) = scope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    fun update(note: Note) = scope.launch(Dispatchers.IO) {
        noteRepository.update(note)
    }

    fun delete(note: Note) = scope.launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}