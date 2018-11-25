package com.muvasia.driver.androidarchitecturecomponents.view.fragments


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.muvasia.driver.androidarchitecturecomponents.database.Note

import com.muvasia.driver.androidarchitecturecomponents.R
import com.muvasia.driver.androidarchitecturecomponents.viewModel.NoteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewNoteFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    companion object {
        val TAG = NewNoteFragment::class.simpleName

        fun newInstance(): NewNoteFragment {
            return NewNoteFragment()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_note, container, false)


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)


        var etTitle = view.findViewById(R.id.etTitle) as EditText
        var etBody = view.findViewById(R.id.etBody) as EditText
        var btnSubmit = view.findViewById(R.id.btnSubmit) as Button

        btnSubmit.setOnClickListener {
            var title = etTitle.text.toString().trim()
            var body = etBody.text.toString().trim()

            if (!title.isEmpty() || !body.isEmpty()) {
                var note = Note(title,body)
                noteViewModel.insert(note)

                activity!!.supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(NoteListFragment.TAG)
                    .replace(R.id.fragment_container, NoteListFragment.newInstance(), NoteListFragment.TAG)
                    .commit()
            }
        }

        return view
    }


}
