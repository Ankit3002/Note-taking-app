package com.example.myapplication.utils

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.Repository.UserRepository
import com.example.myapplication.model.User
import com.example.myapplication.networking.UserServiceNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserConnection :ViewModel() {

    // below ones are used only in signin credentials ...
    val userName : MutableState<String> = mutableStateOf("default")
    val password : MutableState<String> = mutableStateOf("default")

    val signIn_user_response : MutableState<String> = mutableStateOf("default")
    val Created_user_response : MutableState<String> = mutableStateOf("default")
    val delete_user_response :  MutableState<String> = mutableStateOf("default")

    val repo = UserRepository()

    // method to sign in the user...
    fun signInUser(user : User)
    {
        try {
            viewModelScope.launch {
                val result = repo.SignInUser(user)
                signIn_user_response.value = result
            }
        }
        catch(e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())
        }

    }

    // method to create the user...
    fun createUser(user : User)
    {
        try {
            viewModelScope.launch {
                val result = repo.CreateUser(user)
                Created_user_response.value = result
            }
        }
        catch (e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())

        }
    }

    fun deleteUser(id : String)
    {
        try {
            viewModelScope.launch {
                val result = repo.deleteUser(id)
                delete_user_response.value = result
            }
        }
        catch (e : Exception)
        {
            Log.d("Ankit_exception", e.message.toString())

        }
    }
}

