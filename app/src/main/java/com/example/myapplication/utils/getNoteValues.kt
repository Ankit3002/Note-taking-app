package com.example.myapplication.utils

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.model.note
import com.example.myapplication.networking.NoteServiceNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//fun getNoteValues () : List<note>
//{
//    var note_list : List<note> = listOf(
//        note("Nothing", "Sorry", "Won't be able to fetch notes Check Internet Connection")
//    )
//    val note_service_get_note =  NoteServiceNetwork.NoteServiceInstance
//    note_service_get_note.getNotes()?.enqueue(
//        object : Callback<List<note?>?> {
//            override fun onResponse(call: Call<List<note?>?>?,
//                                    response: Response<List<note?>?>
//            ) {
//                note_list = response.body() as List<note> // Now we can use
//            }
//
//            override fun onFailure(call: Call<List<note?>?>?, t: Throwable) {
//                Log.d("error_note_list" , "Won't be able to fetch the headings..")
//            }
//        }
//    )
//
//    return note_list
//}
//
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    // LiveData or State for your note list
    // You can use MutableState or LiveData, depending on your preference.
    // Here's an example with MutableState.
    val noteListState = mutableStateOf<List<note>>(emptyList())
    val noteState = mutableStateOf<note?>(null)
    init {
        fetchNotes()
    }

    public fun fetchNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val notes = getAllNoteValues()
            noteListState.value = notes
        }
    }

    public fun fetchANote(id : String){
        viewModelScope.launch(Dispatchers.IO) {
            val note_value = getANoteValue(id)
            noteState.value = note_value

        }
    }
}




suspend fun getAllNoteValues(): List<note> {
    return withContext(Dispatchers.IO) {
        val note_service_get_note = NoteServiceNetwork.NoteServiceInstance
        val response = note_service_get_note.getNotes()?.execute()

        if (response != null && response.isSuccessful) {
            response.body() as List<note>
        } else {
            // Handle the error or return an empty list
            emptyList()
        }
    }
}


suspend fun getANoteValue(id : String) : note
{
    return withContext(Dispatchers.IO){
        val note_service_get_note =  NoteServiceNetwork.NoteServiceInstance
        val response = note_service_get_note.getNotebyId(id)?.execute()

        if(response!= null && response.isSuccessful){
            response.body() as note
        }
        else {
            note("0","Error", "Internet is not working" )
        }

    }


}
