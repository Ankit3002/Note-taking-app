package com.example.myapplication

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface superheroAPI {
    @GET("marvel") // making get request at marvel end-point
    fun getHeroes(): Call<List<content?>?>?
}

