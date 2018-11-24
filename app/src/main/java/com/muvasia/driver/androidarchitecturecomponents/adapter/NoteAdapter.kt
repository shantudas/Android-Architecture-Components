package com.muvasia.driver.androidarchitecturecomponents.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.muvasia.driver.androidarchitecturecomponents.R
import com.muvasia.driver.androidarchitecturecomponents.database.Note

class NoteAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var notes: ArrayList<Note> = ArrayList()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNoteTitle: TextView = itemView.findViewById(R.id.tvNoteTitle)
        val tvNoteBody: TextView = itemView.findViewById(R.id.tvNoteBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val itemView = inflater.inflate(R.layout.list_each_row_note, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note: Note = notes[position]
        holder.tvNoteTitle.text = note.title
        holder.tvNoteBody.text = note.body
    }

    fun setNoteList(noteList: ArrayList<Note>) {
        this.notes = noteList
        notifyDataSetChanged()
    }
}