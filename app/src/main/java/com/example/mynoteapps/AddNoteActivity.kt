package com.example.mynoteapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapps.component.NoteViewModel
import com.example.mynoteapps.databinding.ActivityAddNoteBinding
import com.example.mynoteapps.domain.model.Note
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    lateinit var viewModel: NoteViewModel
    var noteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")) {
            val noteTile = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1)
            binding.btnAdd.text = "Update Note"
            binding.edtNoteTitle.setText(noteTile)
            binding.edtNoteDesc.setText(noteDescription)

        }else {
            binding.btnAdd.text = "Save Note"
        }

        binding.btnAdd.setOnClickListener {
            val noteTitle = binding.edtNoteTitle.text.toString()
            val noteDescription = binding.edtNoteDesc.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDate)

                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
                }
            }
                startActivity(Intent(applicationContext, MainActivity::class.java))
                this.finish()
        }
    }
}