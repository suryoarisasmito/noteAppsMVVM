package com.example.mynoteapps.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynoteapps.domain.model.Note

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(note: Note)

    @Update
     fun update(note: Note)

    @Delete
     fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
     fun getAllNotes(): LiveData<List<Note>>
}