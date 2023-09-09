package com.example.myapplication.routing

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.home
import com.example.myapplication.notes_edit_screen


@Composable
fun Navigation()
{
    val navController  = rememberNavController()
    NavHost(navController = navController, startDestination = "home" ){

        // edit note ...
        composable(route = "edit_note"){
            // call a function ...
            notes_edit_screen()

        }
        composable(route = "home")
        {
            home(navController)
        }
    }
}