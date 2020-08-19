package com.muvasia.driver.androidarchitecturecomponents.repository

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import com.muvasia.driver.androidarchitecturecomponents.database.Note
import com.muvasia.driver.androidarchitecturecomponents.database.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.fetchAllNotes()

    fun getNoteItem(itemId: Int?): LiveData<Note> {
        return noteDao.getNoteItemById(itemId)
    }


    /**
     *  @WorkerThread annotation, to mark that this method needs to be called from a non-UI thread
     *  suspend modifier to tell the compiler that this needs to be called from a coroutine or another suspending function.
     *
     */
    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    @WorkerThread
    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    @WorkerThread
    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }


}