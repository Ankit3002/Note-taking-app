package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.model.User
import com.example.myapplication.model.note
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.NoteConnection
import com.example.myapplication.utils.NoteViewModel
import com.example.myapplication.utils.UserConnection

//import com.example.myapplication.utils.getNoteValues


/*
 *  create screen of a particular note ...
 */

//class edit_note : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    notes_edit_screen();
//                }
//            }
//        }
//    }
//}


// Main Function ....

@Composable
fun notes_edit_screen(id : String, viewModel_note: NoteConnection, viewModel_user: UserConnection, navController: NavController)
{
    // search on notes over here ... and take the note which is similar to hash id ...
    var note_chosen : note? = null
    for(note_iterator in viewModel_note.noteList.value)
    {
        if(note_iterator.id.equals(id))
        {
            note_chosen = note_iterator
//            break
        }
    }

    val textState_heading = remember { mutableStateOf(note_chosen!!.heading) }
    val textState_message = remember { mutableStateOf(note_chosen!!.message) }


    Column(modifier = Modifier.fillMaxSize() ,  verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        // back and menu button ...
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // add the back button over here ...


            // Add space between the buttons and the right edge of the row
            Spacer(modifier = Modifier.weight(1f))

            // add the menu button over here ...
            IconButton(
                onClick = {
                    // Handle button click here
                    // method to update the note over here ...
                  viewModel_note.updateNote(note(id, textState_heading.value, textState_message.value, User(viewModel_user.userName.value, viewModel_user.password.value)))
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(30.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_cloud_done_24),
                    contentDescription = null
                )
            }
        }

        Column {
            // Create a TextField composable
            OutlinedTextField(
                value = textState_heading.value,
                onValueChange = {
                    // Update the text state when the user types
                    textState_heading.value = it
                },
                label = { Text("Enter heading") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp)
            )

        }


        Column {
            // Create a TextField composable
            OutlinedTextField(
                value = textState_message.value,
                onValueChange = {
                    // Update the text state when the user types
                    textState_message.value = it
                },
                label = { Text("Enter Message") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(500.dp)
            )

        }



        RoundedCornerBox(id, viewModel_note, navController)
    }
}


// notes create screen
@Composable
fun notes_create_screen(navController: NavController, viewModel_user : UserConnection, viewModel_note : NoteConnection)
{
    val textState_heading = remember { mutableStateOf("") }
    val textState_message = remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        // back and menu button ...
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // add the back button over here ...


            // Add space between the buttons and the right edge of the row
            Spacer(modifier = Modifier.weight(1f))

            // add the menu button over here ...
            IconButton(
                onClick = {
                    val user = User(viewModel_user.userName.value, viewModel_user.password.value)
                    viewModel_note.createNote(note("random", textState_heading.value, textState_message.value,user))
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(30.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_cloud_done_24),
                    contentDescription = null
                )
            }

                // run the launch effect to move to the home screen of notes ...
            LaunchedEffect(viewModel_note.created_note_response.value)
            {
                when(viewModel_note.created_note_response.value)
                {
                    "success" -> {
                        // note created successfully...
                        viewModel_note.created_note_response.value = "default"
                        navController.navigate("home")
                    }
                    "failure"-> {
                        navController.navigate("homie")
                    }
                }
            }
        }


        Column {
            // Create a TextField composable
            OutlinedTextField(
                value = textState_heading.value,
                onValueChange = {
                    // Update the text state when the user types
                    textState_heading.value = it
                },
                label = { Text("Enter heading") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp)
            )

        }


        Column {
            // Create a TextField composable
            OutlinedTextField(
                value = textState_message.value,
                onValueChange = {
                    // Update the text state when the user types
                    textState_message.value = it
                },
                label = { Text("Enter Message") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(500.dp)
            )

        }

        // add the bottom  navigation view...
//        RoundedCornerBox()
    }
}







// Text field...
@Composable
fun HeadingOutlineText(note_value : note) {
    Log.d("outline_checking", note_value.heading)
    // Create a state to hold the text entered by the user
    val textState = remember { mutableStateOf(note_value.heading) }

    Column {
        // Create a TextField composable
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                // Update the text state when the user types
                textState.value = it
            },
            label = { Text("Enter heading") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(100.dp)
        )

        // Display the entered text
//        Text(text = "Entered text: ${textState.value}", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun MessageOutlineText(note_value : note) {
    // Create a state to hold the text entered by the user
    val textState = remember { mutableStateOf(note_value.message) }

    Column {
        // Create a TextField composable
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                // Update the text state when the user types
                textState.value = it
            },
            label = { Text("Enter Message") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(500.dp)
        )

        // Display the entered text
//        Text(text = "Entered text: ${textState.value}", modifier = Modifier.padding(16.dp))
    }
}


// bottom navigation over here...
@Composable
fun RoundedCornerBox(id : String, viewModel_note: NoteConnection, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(
                color = Color.Red, // Background color
                shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
            )
    ) {

        // camera, pencil , attach button over here ...
        Row()
        {
            // add the image button over here ...
//         ?

            // list over here..

            IconButton(
                onClick = {
                    // Handle button click here
                    // write the logic to delete the note using the id ...
                    viewModel_note.deleteNote(id)
                },
                modifier = Modifier.padding(16.dp)
            ){

                Icon(imageVector =
                ImageVector.vectorResource(id = R.drawable.baseline_delete_24), contentDescription = null)

            }

        }
        LaunchedEffect( viewModel_note.delet_note_response.value )
        {
            when(viewModel_note.delet_note_response.value)
            {
                "success"->{
                    // head over to home screen of notes...
                    viewModel_note.delet_note_response.value = "default"
                    navController.navigate("home")
                }
            }

        }


    }
}

