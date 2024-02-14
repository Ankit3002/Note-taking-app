package com.example.myapplication.utils

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.Repository.NoteRepository
import com.example.myapplication.model.User
import com.example.myapplication.model.note
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class NoteConnection : ViewModel() {

    val noteList : MutableState<List<note>> = mutableStateOf(listOf())
    val fetchNote_response : MutableState<String> = mutableStateOf("nope")
    private val _isDataFetched = MutableLiveData<Boolean>()
    val isDataFetched: LiveData<Boolean> get() = _isDataFetched


    // variables for note creation response ...
    val created_note_response : MutableState<String> = mutableStateOf("default")

    // variables for note updation response...
    val update_note_response : MutableState<String> = mutableStateOf("default")

    // variable for note deletion response...
    val delet_note_response : MutableState<String> = mutableStateOf("default")

    // method to fetch the notes associated with the user ...
    val repo = NoteRepository()
    fun fetchNoteUser(user : User)
    {
        try {
            viewModelScope.launch {
                Log.d("Searching_username", user.name)
                Log.d("Searching_pass", user.password)

                val result = repo.fetchNoteUser(user)
                result.enqueue(object : retrofit2.Callback<List<note>> {
                    override fun onResponse(call: Call<List<note>>, response: Response<List<note>>) {
//                TODO("Not yet implemented")
                        if(response.isSuccessful)
                        {
                            if(response.body()!!.size > 0)
                            {
                                val d = response.body()
                                _isDataFetched.value = true

                                noteList.value = response.body()!!
                            }


                        }

                    }

                    override fun onFailure(call: Call<List<note>>, t: Throwable) {
//                TODO("Not yet implemented")
                        Log.d("gayatri", "not able to connect to internet")
                    }

                })

            }

        }
        catch (e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())
        }
    }


    // method to create the note over here ...
    fun createNote(noteValue : note)
    {
        try {
            viewModelScope.launch {
                Log.d("library_south", noteValue.user.name)
                Log.d("library_south", noteValue.user.password)
                Log.d("library_south", noteValue.message)
                Log.d("library_south", noteValue.heading)


                val result = repo.createNote(noteValue)
                created_note_response.value = result
            }
        }
        catch (e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())
        }
    }

    // method to update the note over here ...
    fun updateNote(noteValue : note)
    {
        try {
            viewModelScope.launch {
                val result = repo.updateNote(noteValue)
                update_note_response.value = result
            }
        }
        catch (e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())
        }
    }

    // method to delete the note over here ..

    fun deleteNote(id : String)
    {
        try {
            viewModelScope.launch {
                val result =  repo.deleteNote(id)
                delet_note_response.value = result

            }

        }
        catch (e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())

        }
    }

}