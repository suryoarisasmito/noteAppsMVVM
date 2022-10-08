package com.example.mynoteapps

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapps.databinding.NoteItemBinding
import com.example.mynoteapps.domain.model.Note

class NoteAdapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val listNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = listNotes[position]
        holder.tvNote.text = note.noteTitle
        holder.tvTime.text = "Last Update : ${note.timeStamp}"

        holder.imgDelete.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(note)
        }

        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(note)

        }
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    fun updateList(newList: List<Note>){
        listNotes.clear()
        listNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvNote: TextView = view.findViewById(R.id.tv_note_title)
        val tvTime: TextView = view.findViewById(R.id.tv_timestamp)
        val imgDelete: ImageView = view.findViewById(R.id.img_delete)
    }

}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}