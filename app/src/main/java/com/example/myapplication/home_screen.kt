package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.model.User
import com.example.myapplication.model.note
import com.example.myapplication.networking.NoteServiceNetwork
import com.example.myapplication.routing.Navigation
import com.example.myapplication.routing.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.NoteConnection
import com.example.myapplication.utils.NoteViewModel
import com.example.myapplication.utils.UserConnection
//import com.example.myapplication.utils.getAllNoteValues
//import com.example.myapplication.utils.getNoteValues
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.poppinsFamily
import com.example.myapplication.ui.theme.strikeFontFamily


// Api ---> create note --> https:// note_taking.app.com/notes/create
// Api --> update --> https:// note_taking.app.com/notes/edit
// Api --> delete --> https:// note_taking.app.com/notes/delete
// Api --> list/fetch --> https:// note_taking.app.com/notes

class home_screen : ComponentActivity() {

    lateinit var navcontroller : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Navigation()
//                    get_home_data()
                }
            }
        }
    }
}

@Preview
@Composable
fun lund(){
    Box(
        modifier = Modifier
            .clickable {
                // Handle click action
            }
            .size(100.dp)
            .background(shape = CircleShape, color = Color.White)
            .padding(12.dp) // Adjust padding as needed
    ) {
        Image(
            painter = painterResource(id = R.drawable.create_button),
            contentDescription = "clouds",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }

}


// this one is the home screen ...
@Composable
fun NotesUser_Screen(viewModel_note : NoteConnection ,viewModel_user : UserConnection, navController: NavController)
{

    val isDataFetched by viewModel_note.isDataFetched.observeAsState(initial = false)

    // add a column for full screen ...
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(10.dp)) {

        /// new code
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // add the photo..
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(
//                        color = Color.Black, // Background color of the circle
////                        shape = CircleShape
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//
//                )
//            }
            Box(modifier =
            Modifier.background(shape = CircleShape, color = Color.Black).size(100.dp).padding(1.dp)){
                Image(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().clip(CircleShape))
            }

            // add the name ... hard coded for now...
            Text(
                text = "  Hi " + viewModel_user.userName.value,
                modifier = Modifier
                    .weight(1f) // Occupy available space
                    .align(Alignment.CenterVertically) // Center vertically
//            ,  style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
                       , style = MaterialTheme.typography.h5
                , fontFamily = strikeFontFamily,
                color = Color.Black
            )

            Box(
                modifier = Modifier
                    .clickable {
                        // Handle click action
                        navController.navigate("create_note")
                    }
                    .size(100.dp)
                    .background(shape = CircleShape, color = Color.Black)
                    .padding(1.dp) // Adjust padding as needed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.create_button),
                    contentDescription = "clouds",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }



        }

        Spacer(modifier = Modifier.height(30.dp))

        // add archive heading over here...
        Row{
            Text("My Notes",
                style = MaterialTheme.typography.h3
                , fontFamily = strikeFontFamily,
                color = Color.Black
//                modifier = Modifier.align(Alignment.CenterHorizontally)

//                style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(100.dp))

            // button to refresh the notes over here...
            IconButton(
                onClick = {
                    // Handle button click here
                      // code to fetch the notes over here...
                      viewModel_note.fetchNoteUser(User(viewModel_user.userName.value, viewModel_user.password.value))
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(40.dp)
            ) {
                // You can use an ImageVector or a Drawable
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_refresh_24),
                    contentDescription = null
                )
            }

        }
        if(viewModel_note.noteList.value.size > 0)
            LazyColumnExample(navController = navController, note_list =viewModel_note.noteList.value )

    }
}


// card to show one note --- a smaller one home screen ...
@Composable
fun card_visible(note_value :note ,  navController: NavController)
{
//     var navController : NavController = rememberNavController()
    Box(
        modifier = Modifier
//            .width(200.dp)// Adjust width    as needed
            .clickable {
                navController.navigate("edit_note/" + note_value.id)
            }
            .height(320.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#9336B4")),
                        Color(android.graphics.Color.parseColor("#40128B")) // Transparent color for the top right
                    ),
                    start = Offset(0f, 1f),
                    end = Offset(1f, 0f),
                    tileMode = TileMode.Clamp
                ),
                shape = RoundedCornerShape(20.dp)
            )
//            .clip(RoundedCornerShape(20.dp))
//            .background(
//                color = Color(android.graphics.Color.parseColor("#9336B4")), // Background color
//                shape = RoundedCornerShape(20.dp) // Adjust the corner radius as needed
//            )
    ) {

        Column(modifier = Modifier.padding(10.dp)) {
            Text(note_value.heading,
                style = TextStyle(
                    fontFamily = strikeFontFamily,
                    fontSize = 25.sp,
                    color = Color.White
                )
            )
            Text(note_value.message,
                style = TextStyle(
                    fontFamily = poppinsFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                    , color = Color.White
                )
            )
        }

    }
}





@Composable
fun LazyRowExample() {
    val items = (1..2).toList() // Replace with your list of items

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items) { item ->
            RowItem(item)
        }
    }
}

@Composable
fun RoundedCornerBoxRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Adjust width as needed
            .padding(16.dp)
            .background(
                color = Color.Red, // Background color
                shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
            )
    ) {
        Text("All (20)")
    }
}


@Composable
fun RowItem(item: Int ) {
    RoundedCornerBoxRow()
}


// scrollable column for now ...
@Composable
fun LazyColumnExample(navController: NavController , note_list : List<note> ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(note_list) { item ->
            ColumnItem(item , navController)
        }
    }
}

@Composable
fun ColumnItem(note_value : note, navController: NavController) {
    if (note_value != null) {
        card_visible(note_value, navController)
    }

}




