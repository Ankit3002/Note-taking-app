package com.example.myapplication.utils

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                            val d = response.body()
                            _isDataFetched.value = true
                            if (d != null) {
                                Log.d("gayatri", d.get(0).heading )
                            }
                            noteList.value = response.body()!!
                        }

                    }

                    override fun onFailure(call: Call<List<note>>, t: Throwable) {
//                TODO("Not yet implemented")
                        Log.d("gayatri", "not able to connect to internet")
                    }

                })
//                noteList.value = result

//                noteList.value = result.execute().body()!!

//                if(result.size > 0)
//                {
//                    Log.d("gayatri", noteList.value.get(0).heading )
//                    fetchNote_response.value = "success"
//                    _isDataFetched.value = true
//                }
//                else
//                {
//                    Log.d("gayatri", "nhi aya kuchh")
//                    fetchNote_response.value = "failure"
//                    _isDataFetched.value = false
//                }
            }

        }
        catch (e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())
        }
    }

}