package com.example.myapplication.networking

import android.util.Log
import android.widget.Toast
import com.example.myapplication.content
import com.example.myapplication.model.User
import com.example.myapplication.model.note
import com.example.myapplication.superheroAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

//const val BASE_URL = "https://notemanagementservice-production.up.railway.app/api/v1/"
// https://notemanagementservice-production.up.railway.app/api/v1/notes
const val BASE_URL = "https://notemanagementservice.onrender.com/api/v1/"

interface NoteService {
//    @GET("notes")
//    fun getNotes(): Call<List<note?>?>?
//

    @POST("notes/{noteId}")
    suspend fun deleteNote(@Path("noteId") id : String) : String


    @PUT("notes")
    suspend fun updateNote(@Body updatedNote: note): String

//    @DELETE("notes/{noteId}")
//    fun deleteNote(@Path("noteId") noteId: String): Call<Void>

    @POST("notes")
    suspend fun createNote(@Body newNote: note): String

//    @GET("notes/{noteId}")
//    fun getNotebyId(@Path("noteId") noteId: String): Call<note>

    @POST("notes/user")
    fun getAllNotesUser(@Body user : User) : Call<List<note>>

}

object NoteServiceNetwork{
    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit : Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val noteService : NoteService by lazy {
        retrofit.create(NoteService::class.java)
    }
}

//object NoteServiceNetwork{
//    val NoteServiceInstance : NoteService
//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        // initailizing it for Note service ...
//        NoteServiceInstance = retrofit.create(NoteService :: class.java)
//    }
//
//}

