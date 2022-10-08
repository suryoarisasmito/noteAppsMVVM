package com.example.mynoteapps.domain.repository

import androidx.lifecycle.LiveData
import com.example.mynoteapps.data_source.NotesDao
import com.example.mynoteapps.domain.model.Note

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()


    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }
}