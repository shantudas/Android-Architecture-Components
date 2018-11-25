package com.muvasia.driver.androidarchitecturecomponents.view.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.muvasia.driver.androidarchitecturecomponents.R
import com.muvasia.driver.androidarchitecturecomponents.adapter.NoteAdapter
import com.muvasia.driver.androidarchitecturecomponents.viewModel.NoteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NoteListFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    companion object {
        val TAG = NoteListFragment::class.simpleName

        fun newInstance(): NoteListFragment {
            return NoteListFragment()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_note_list, container, false)

        val activity = activity
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_note_list)
        val adapter = NoteAdapter(activity!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        // Inflate the layout for this fragment


        noteViewModel.allNotes.observe(this, Observer { notes ->
            adapter.setNoteList(notes!!)
        })

        return view

    }


}
