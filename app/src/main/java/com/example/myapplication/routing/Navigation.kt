package com.example.myapplication.routing

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.HomeScreen
import com.example.myapplication.NotesUser_Screen
import com.example.myapplication.SignInScreen
import com.example.myapplication.SignUpScreen
//import com.example.myapplication.home
//import com.example.myapplication.notes_create_screen
//import com.example.myapplication.notes_edit_screen
import com.example.myapplication.utils.NoteConnection
import com.example.myapplication.utils.UserConnection


@Composable
fun Navigation()
{
    val viewModel_user = viewModel(UserConnection::class.java)
    // same create the view model object for note over here ...
    val viewModel_note = viewModel(NoteConnection::class.java)

    val navController  = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen" ){

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
//                notes_edit_screen(id)
                Log.d("Ankit_exception", "nothing to show")
            }

        }
        // home screen note ...
        composable(route = "home")
        {
            NotesUser_Screen(viewModel_note, viewModel_user, navController)
        }
        // create note ...
//        composable(route = "create_note"){
//            notes_create_screen(navController)
//        }

        // composable for sign up screen over here...
        composable(route = "signUp")
        {
            // start a composable over here to render the ui ...
            SignUpScreen(viewModel = viewModel_user, navController = navController)
        }

        composable(route = "signIn")
        {
            SignInScreen(viewModel_user, navController, viewModel_note)
        }

        composable(route = "homie")
        {
            Text("User Already exists", style =  TextStyle(fontSize = 34.sp))
        }

        composable(route = "splash_screen")
        {
            HomeScreen(viewModel = viewModel_user, navController = navController )
        }



    }
}