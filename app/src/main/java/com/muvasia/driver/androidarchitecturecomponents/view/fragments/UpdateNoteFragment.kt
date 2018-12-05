package com.muvasia.driver.androidarchitecturecomponents.view.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.muvasia.driver.androidarchitecturecomponents.R
import com.muvasia.driver.androidarchitecturecomponents.database.Note
import com.muvasia.driver.androidarchitecturecomponents.viewModel.NoteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UpdateNoteFragment : Fragment() {

    companion object {
        val TAG = UpdateNoteFragment::class.simpleName
        const val NOTE_ID = "NOTE_ID"

        fun newInstance(): UpdateNoteFragment {
            return UpdateNoteFragment()
        }

    }

    private lateinit var noteViewModel: NoteViewModel
    private var noteID: Int? = null
    private var noteTitle: String? = null
    private var noteBody: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_note, container, false)

        getNoteID()

        var etUpdateTitle = view.findViewById(R.id.etUpdateTitle) as EditText
        var etUpdateBody = view.findViewById(R.id.etUpdateBody) as EditText
        var btnUpdateNote = view.findViewById(R.id.btnUpdateNote) as Button

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.getNoteItem(noteID).observe(this, Observer { note ->

            noteTitle = note?.title
            noteBody = note?.body

            etUpdateTitle.text = noteTitle?.toEditable()
            etUpdateBody.text = noteBody?.toEditable()

        })

        btnUpdateNote.setOnClickListener {
            noteTitle = etUpdateTitle.text.toString().trim()
            noteBody = etUpdateBody.text.toString().trim()

            if (!noteTitle.isNullOrEmpty() || !noteBody.isNullOrEmpty()) {
                val note = Note(noteTitle!!, noteBody!!)
                note.id = noteID!!
                noteViewModel.update(note)

                // send to NoteList page
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(NoteListFragment.TAG)
                    .replace(R.id.fragment_container, NoteListFragment.newInstance(), NoteListFragment.TAG)
                    .commit()

            } else {
                // TODO :: verification process need to implement
            }

        }



        return view
    }

    /**
     * TextView class contains a getter CharSequence getText() and a setter void setText(CharSequence).
     * If I had a variable of type TextView your code would work fine.
     * But I have a variable of type EditText.
     * And the EditText class contains an overridden getter Editable getText(),
     * which means that I can get an Editable for an EditText and set an Editable to an  EditText.
     * Therefore, Kotlin reasonably creates a synthetic property text of type Editable.
     * The String class is not Editable,
     * that's why you cannot assign a String instance to the text property of the EditText class.
     *
     * To avoid type mismatch, we can use the Factory inner class of Editable class.
     *
     * @param @null
     *
     */
    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    /**
     * get note id from NoteListFragment via bundle
     *
     * @param @null
     */
    private fun getNoteID() {
        if (arguments?.getInt("NOTE_ID") != null) {
            noteID = arguments?.getInt("NOTE_ID")
            Log.d(TAG, " NoteID :: " + noteID)
        }
    }


}
