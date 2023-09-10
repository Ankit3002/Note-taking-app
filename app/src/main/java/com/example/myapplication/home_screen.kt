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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.model.note
import com.example.myapplication.networking.NoteServiceNetwork
import com.example.myapplication.routing.Navigation
import com.example.myapplication.routing.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.getNoteValues
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
                    get_home_data()
                }
            }
        }
    }


    /*
     * function to fetch data using retrofit...
     */
   fun get_home_data()
   {
       var retrofit = Retrofit.Builder()
           .baseUrl("https://simplifiedcoding.net/demos/")
           .addConverterFactory(GsonConverterFactory.create())
           .build();  // Part 1

       val api = retrofit.create(superheroAPI::class.java);


       api.getHeroes()?.enqueue(
           object : Callback<List<content?>?> {
               override fun onResponse(call: Call<List<content?>?>?,
                                       response: Response<List<content?>?>) {
                   val heroList: List<content> = response.body() as List<content> // Now we can use

                   for(value in heroList)
                   {
                       Log.d("ankit", value.realname)

                   }

               }

               override fun onFailure(call: Call<List<content?>?>?, t: Throwable) {
                   Toast.makeText(applicationContext, t.message,
                       Toast.LENGTH_SHORT).show()
               }
           }
       )


   }


}



// Main function ...
@Composable
fun home(navController: NavController)
{

//    val note_list = getNoteValues()
//    for(note in note_list )
//    {
//        Log.d("feature_ankit", note.heading)
//    }

    val note_list = listOf(
        note("1","first heading ", "first message"),
        note("2","Second heading ", "Second message")
    )

    // add a column for full screen ...
    Column(modifier =  Modifier.fillMaxSize()
        .padding(10.dp)) {

        /// new code
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // add the photo..
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = Color.Black, // Background color of the circle
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.matt),
                    contentDescription = null,
                    modifier = Modifier.clip(CircleShape)
                )
            }

            // add the name ... hard coded for now...
            Text(
                text = "   Hi, Matt ",
                modifier = Modifier
                    .weight(1f) // Occupy available space
                    .align(Alignment.CenterVertically) // Center vertically
            ,  style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
            )

            IconButton(
                onClick = {
                    // Handle button click here
                    navController.navigate("create_note")
                },
                modifier = Modifier.padding(16.dp).size(40.dp)
            ) {
                // You can use an ImageVector or a Drawable
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_circle_24),
                    contentDescription = null
                )
            }
        }


        // add archive heading over here...
        Text("My Notes", style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold))

        // add scrollable list horizontal for filter...
//        LazyRowExample()

        // add the grid in the below region ...
        LazyColumnExample(navController , note_list )

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
                navController.navigate("edit_note/0")
            }
            .height(350.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .background(
                color = Color.Red, // Background color
                shape = RoundedCornerShape(20.dp) // Adjust the corner radius as needed
            )
    ) {

        Column(modifier = Modifier.padding(10.dp)) {
            Text(note_value.heading, style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp))

            Text(note_value.message)

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



// scrollable grid .. below..
//
//@Composable
//fun ScrollableGrid() {
//    val items = (1..100).toList() // Replace with your grid items
//
//    LazyVerticalGrid(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//    ) {
//        items(items) { item ->
//            GridItem(item = item)
//        }
//    }
//}
//
//@Composable
//fun GridItem(item: Int) {
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth(),
//        shape = MaterialTheme.shapes.medium, // Adjust the shape as needed
//        elevation = 4.dp // Elevation for the card
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .padding(16.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = "Item $item")
//        }
//    }
//}
//
//@Preview
//@Composable
//fun ScrollableGridPreview() {
//    ScrollableGrid()
//}




