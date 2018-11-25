package com.muvasia.driver.androidarchitecturecomponents.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.muvasia.driver.androidarchitecturecomponents.database.Note
import com.muvasia.driver.androidarchitecturecomponents.database.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.fetchAllNotes()


    /**
     *  @WorkerThread annotation, to mark that this method needs to be called from a non-UI thread
     *  suspend modifier to tell the compiler that this needs to be called from a coroutine or another suspending function.
     *
     */
    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }
}