package com.example.myapplication.Repository

import com.example.myapplication.model.User
import com.example.myapplication.networking.UserServiceNetwork
import retrofit2.Response

// mention all the api' over here...


class UserRepository {
    private val userService = UserServiceNetwork.userService

    suspend fun SignInUser(user: User) : String {
        return userService.SignInUser(user)
    }

    // method to create the user ..
    suspend fun CreateUser(user : User) : String
    {
        return userService.CreateUser(user)
    }


    // method to delete the user ..
    suspend fun deleteUser(id : String) : String
    {
        return userService.deleteUser(id)
    }
    //
}
