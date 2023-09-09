package com.example.myapplication.utils

import android.util.Log
import com.example.myapplication.model.note
import com.example.myapplication.networking.NoteServiceNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getNoteValues () : List<note>
{
    var note_list : List<note> = listOf(
        note("Nothing", "Sorry", "Won't be able to fetch notes Check Internet Connection")
    )
    val note_service_get_note =  NoteServiceNetwork.NoteServiceInstance
    note_service_get_note.getNotes()?.enqueue(
        object : Callback<List<note?>?> {
            override fun onResponse(call: Call<List<note?>?>?,
                                    response: Response<List<note?>?>
            ) {
                note_list = response.body() as List<note> // Now we can use

//                for(value in note_list)
//                {
//                    Log.d("heading ", value.heading)
////                    Log.d("ankit", value.realname)
//
//                }

            }

            override fun onFailure(call: Call<List<note?>?>?, t: Throwable) {
                Log.d("error_note_list" , "Won't be able to fetch the headings..")
            }
        }
    )
    return note_list
}
