package com.muvasia.driver.androidarchitecturecomponents.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.muvasia.driver.androidarchitecturecomponents.R
import com.muvasia.driver.androidarchitecturecomponents.adapter.NoteAdapter
import com.muvasia.driver.androidarchitecturecomponents.database.Note
import com.muvasia.driver.androidarchitecturecomponents.view.fragments.NewNoteFragment
import com.muvasia.driver.androidarchitecturecomponents.view.fragments.NoteListFragment
import com.muvasia.driver.androidarchitecturecomponents.viewModel.NoteViewModel

class MainActivity : AppCompatActivity() {

    /*private val note: Note
    private lateinit var noteViewModel: NoteViewModel

    init {
        note = Note("This is an another title", "This is an another body")
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, NoteListFragment.newInstance(), NoteListFragment.TAG)
                .commit()
        }

        val fab: View = findViewById(R.id.fabAddNewNote)
        fab.setOnClickListener { view ->
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(NewNoteFragment.TAG)
                .replace(R.id.fragment_container, NewNoteFragment.newInstance(), NewNoteFragment.TAG)
                .commit()
        }

        /*val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = NoteAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.insert(note)

        Log.d("all notes", noteViewModel.allNotes.toString())

        noteViewModel.allNotes.observe(this, Observer { notes ->
            //            adapter.setNoteList(((notes as ArrayList<Note>?)!!))
            adapter.setNoteList(notes!!)
        })*/
    }

}
