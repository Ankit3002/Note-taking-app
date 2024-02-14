package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class note(
//    @SerializedName("id")
    val id : String ,
//    @SerializedName("heading")
    val heading : String,
//    @SerializedName("message")
    val message : String ,
//    @SerializedName("user")
    val user : User)
