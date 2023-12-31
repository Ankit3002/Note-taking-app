package com.example.myapplication.networking

import android.util.Log
import android.widget.Toast
import com.example.myapplication.content
import com.example.myapplication.model.note
import com.example.myapplication.superheroAPI
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "https://notemanagementservice-production.up.railway.app/api/v1/"
// https://notemanagementservice-production.up.railway.app/api/v1/notes

interface NoteService {
    @GET("notes")
    fun getNotes(): Call<List<note?>?>?

    @PUT("notes")
    fun updateNote(@Body updatedNote: note): Call<note>

    @DELETE("notes/{noteId}")
    fun deleteNote(@Path("noteId") noteId: String): Call<Void>

    @POST("notes")
    fun createNote(@Body newNote: note): Call<String>

    @GET("notes/{noteId}")
    fun getNotebyId(@Path("noteId") noteId: String): Call<note>

}

object NoteServiceNetwork{
    val NoteServiceInstance : NoteService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // initailizing it for Note service ...
        NoteServiceInstance = retrofit.create(NoteService :: class.java)
    }

}

