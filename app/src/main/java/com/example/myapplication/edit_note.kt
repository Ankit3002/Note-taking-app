package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyApplicationTheme


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
fun notes_edit_screen()
{
    Column(modifier = Modifier.fillMaxSize())
    {
        // back and menu button ...
        Row(modifier =  Modifier.fillMaxWidth()) {

            // add the back button over here ...
            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ) {
                // You can use an ImageVector or a Drawable
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_ios_24), contentDescription = null)

            }

            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ) {

                // add the menu button over here ...
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_menu_24), contentDescription = null)
            }
        }


        // icon button row ends over here...
        // add the input text field for heading ...
        TextFieldOutline()

        // add the blank field for content...
        TextFieldOutline()
        // add the bottom  navigation view...
        RoundedCornerBox()
    }
}






// Text field...
@Composable
fun TextFieldOutline() {
    // Create a state to hold the text entered by the user
    val textState = remember { mutableStateOf("") }

    Column {
        // Create a TextField composable
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                // Update the text state when the user types
                textState.value = it
            },
            label = { Text("Enter text") },
            modifier = Modifier.padding(16.dp)
        )

        // Display the entered text
        Text(text = "Entered text: ${textState.value}", modifier = Modifier.padding(16.dp))
    }
}


// bottom navigation over here...
@Composable
fun RoundedCornerBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Adjust width as needed
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

            // edit over here..
            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ){
                Icon(imageVector =
                ImageVector.vectorResource(id = R.drawable.baseline_edit_24), contentDescription = null)
            }

            // attach over here..
            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ){
                Icon(imageVector =
                ImageVector.vectorResource(id = R.drawable.baseline_attach_file_24), contentDescription = null)
            }

            // list over here..

            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ){

                Icon(imageVector =
                ImageVector.vectorResource(id = R.drawable.baseline_format_list_bulleted_24), contentDescription = null)

            }

        }
    }
}

