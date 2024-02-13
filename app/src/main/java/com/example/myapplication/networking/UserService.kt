package com.example.myapplication.networking

import com.example.myapplication.model.User
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface UserService {

    // api for sign in
    @POST("user/signin")
    suspend fun SignInUser(@Body user : User) : String

    // api for sign up
    @POST("user")
    suspend fun CreateUser(@Body user : User) : String


    //api for delete the particular user..
    @POST("user/{userId}")
    suspend fun deleteUser(@Path("userId") id : String) : String

}

object UserServiceNetwork{
        var gson = GsonBuilder()
            .setLenient()
            .create()
    private val retrofit : Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val userService : UserService by lazy{
        retrofit.create(UserService::class.java)
    }
}




