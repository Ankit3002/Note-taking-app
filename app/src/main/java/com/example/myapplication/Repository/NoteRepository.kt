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
//        var note_list : List<note> = listOf()

//        noteService.getAllNotesUser(user).enqueue(object : Callback<List<note>>{
//            override fun onResponse(call: Call<List<note>>, response: Response<List<note>>) {
////                TODO("Not yet implemented")
//                if(response.isSuccessful)
//                {
//                    note_list = response.body()!!
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<note>>, t: Throwable) {
////                TODO("Not yet implemented")
//                Log.d("gayatri", "not able to connect to internet")
//            }
//
//        })

//        return note_list
    }

}