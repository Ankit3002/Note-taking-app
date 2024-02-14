package com.example.myapplication.Repository

import android.util.Log
import com.example.myapplication.model.User
import com.example.myapplication.model.note
import com.example.myapplication.networking.NoteServiceNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteRepository {

    private val noteService = NoteServiceNetwork.noteService

    // suspend function to fetch notes from the network..
    suspend fun fetchNoteUser(user : User) : Call<List<note>>
    {
        return noteService.getAllNotesUser(user)
    }

    // method to create the note ...
    suspend fun createNote(noteValue: note) : String
    {
        val res = noteService.createNote(newNote = noteValue)
        Log.d("library_", res)
        return  res
    }

    // method to update the note over here...
    suspend fun updateNote(noteValue : note) : String
    {
        return noteService.updateNote(noteValue)
    }

    suspend fun deleteNote(id : String) : String
    {
        return noteService.deleteNote(id)
    }
}