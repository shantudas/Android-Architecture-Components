package com.muvasia.driver.androidarchitecturecomponents.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.muvasia.driver.androidarchitecturecomponents.R
import com.muvasia.driver.androidarchitecturecomponents.adapter.NoteAdapter
import com.muvasia.driver.androidarchitecturecomponents.database.Note
import com.muvasia.driver.androidarchitecturecomponents.viewModel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private val note: Note
    private lateinit var noteViewModel: NoteViewModel

    init {
        note= Note("This is a title 2","This is body 2")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = NoteAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.insert(note)

        Log.d("all notes", noteViewModel.allNotes.toString())

        noteViewModel.allNotes.observe(this, Observer { notes ->
            adapter.setNoteList(((notes as ArrayList<Note>?)!!))
        })
    }
}
