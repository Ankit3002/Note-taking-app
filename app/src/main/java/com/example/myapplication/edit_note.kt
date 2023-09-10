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
import com.example.myapplication.model.note
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.NoteViewModel
import com.example.myapplication.utils.createNoteValue
import com.example.myapplication.utils.getANoteValue
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
fun notes_edit_screen(id : String)
{
    // fetch the particular note from the id ...
    val viewModel: NoteViewModel = viewModel()
//    val nodestate by viewModel.noteState
    LaunchedEffect(id){
        viewModel.fetchANote(id)
    }
    val note_value by viewModel.noteState


//    val note_value =  viewModel.noteState
//    val note_value = getANoteValue(id)

    //mocking note object for now ...
//    val note_value = note("123", "edit screen created", "message is nothing")
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



        // icon button row ends over here...
        // add the input text field for heading ...
        note_value?.let { HeadingOutlineText(note_value = it) }

        // add the blank field for content...
        note_value?.let { MessageOutlineText(note_value = it) }
        // add the bottom  navigation view...
        RoundedCornerBox()
    }
}


// notes create screen
@Composable
fun notes_create_screen(navController: NavController)
{
    val textState_heading = remember { mutableStateOf("") }
    val textState_message = remember { mutableStateOf("") }
    val viewModel: NoteViewModel = viewModel()
//    val nodestate by viewModel.noteState

//    val note_value by viewModel.noteState

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
                    // Handle button click here
                    // create note object out of current ...
                    val note_value : note = note("anything", textState_heading.value, textState_message.value)
                    val note_id = viewModel.createNoteCo(note_value)
                    if(!note_id.equals("failure"))
                    {
                        navController.navigate("edit_note/"+note_id)
                    }

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



        // icon button row ends over here...
        // add the input text field for heading ...
//        HeadingOutlineText(note_value = note("123", "", ""))



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

            // Display the entered text
//        Text(text = "Entered text: ${textState.value}", modifier = Modifier.padding(16.dp))
        }

        // add the blank field for content...
//        MessageOutlineText(note_value = note("123" , "", ""))


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

            // Display the entered text
//        Text(text = "Entered text: ${textState.value}", modifier = Modifier.padding(16.dp))
        }

        // add the bottom  navigation view...
        RoundedCornerBox()
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
fun RoundedCornerBox() {
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
            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ) {
                // You can use an ImageVector or a Drawable
                Icon(imageVector =
                ImageVector.vectorResource(id = R.drawable.baseline_camera_alt_24), contentDescription = null)



            }

            // list over here..

            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ){

                Icon(imageVector =
                ImageVector.vectorResource(id = R.drawable.baseline_delete_24), contentDescription = null)

            }

        }
    }
}

