package com.example.myapplication.routing

import com.example.myapplication.utils.Constants


sealed class Screen(val route:String) {

    object home_screen : Screen(Constants.NavigationStrings.home_screen_value)
    object edit_note : Screen(Constants.NavigationStrings.edit_note_value)

}