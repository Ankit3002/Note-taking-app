package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting2("Android")
                    home_screen(34)
                    get_home_data()
                }
            }
        }
    }

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



@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyApplicationTheme {
        Greeting2("Android")
    }
}


@Composable
fun home_screen(response: Int)
{
    // add a column for full screen ...
    Column(modifier =  Modifier.fillMaxSize()) {

        Row(modifier = Modifier.fillMaxWidth()) {

            // add the photo..


            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        color = Color.Gray, // Background color of the circle
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter =painterResource(id = R.drawable.matt), contentDescription = null,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            // add the name ... hard coded for now...
            Text(text = "Hi, Matt ")

            IconButton(
                onClick = {
                    // Handle button click here
                },
                modifier = Modifier.padding(16.dp)
            ) {
                // You can use an ImageVector or a Drawable
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_menu_24), contentDescription = null)

            }
        }

        // add archive heading over here...
        Text(response.toString())

        // add scrollable list horizontal for filter...
        LazyRowExample()

        // add the grid in the below region ...
        LazyColumnExample()

    }
}




@Composable
fun LazyRowExample() {
    val items = (1..50).toList() // Replace with your list of items

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
fun RowItem(item: Int) {
    // Customize the content of each row item here
    // For example:
    // add box over here...
    RoundedCornerBoxRow()
//    Text(
//        text = "Item $item",
//        modifier = Modifier.padding(16.dp)
//    )
}


// scrollable column for now ...
@Composable
fun LazyColumnExample() {
    val items = (1..20).toList() // Replace with your list of items

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { item ->
            ColumnItem(item)
        }
    }
}

@Composable
fun ColumnItem(item: Int) {
    // Customize the content of each column item here
    // For example:
    Text(
        text = "Item $item",
        modifier = Modifier.padding(16.dp)
    )
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




