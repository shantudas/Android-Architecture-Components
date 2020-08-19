package com.muvasia.driver.androidarchitecturecomponents.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.muvasia.driver.androidarchitecturecomponents.R
import com.muvasia.driver.androidarchitecturecomponents.view.fragments.NewNoteFragment
import com.muvasia.driver.androidarchitecturecomponents.view.fragments.NoteListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /**
         * Load NoteListFragment to show note list
         *
         */
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, NoteListFragment.newInstance(), NoteListFragment.TAG)
                .commit()
        }


        /**
         *  Fab button initialization
         *  On Click action go to NewNoteFragment to create a new Note
         *
         */
        val fab: View = findViewById(R.id.fabAddNewNote)
        fab.setOnClickListener { view ->
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(NewNoteFragment.TAG)
                .replace(R.id.fragment_container, NewNoteFragment.newInstance(), NewNoteFragment.TAG)
                .commit()
        }
    }

}
