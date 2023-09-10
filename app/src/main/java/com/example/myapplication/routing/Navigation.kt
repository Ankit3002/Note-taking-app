package com.example.myapplication.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.home
import com.example.myapplication.notes_create_screen
import com.example.myapplication.notes_edit_screen


@Composable
fun Navigation()
{
    val navController  = rememberNavController()
    NavHost(navController = navController, startDestination = "home" ){

        // edit note ...
        composable(route = "edit_note/{id}" , arguments = listOf(
            navArgument("id"){
                type = NavType.StringType
            }
        )){
            backstackEntry->
            val id = requireNotNull(backstackEntry.arguments).getString("id")
            // call a function ...
            if (id != null) {
                notes_edit_screen(id)
            }

        }
        // home screen note ...
        composable(route = "home")
        {
            home(navController)
        }
        // create note ...
        composable(route = "create_note"){
            notes_create_screen(navController)
        }

    }
}