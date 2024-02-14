package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.User
import com.example.myapplication.routing.Navigation
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.UserConnection
import androidx.compose.runtime.collectAsState
import com.example.myapplication.Repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.myapplication.ui.theme.poppinsFamily
import com.example.myapplication.ui.theme.strikeFontFamily
import com.example.myapplication.utils.NoteConnection

//import androidx.compose.runtime.livedata.observeAsState


class SignUp :  ComponentActivity(){

    private val viewModel : UserConnection by viewModels()

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
//                    HomeScreen()


                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel : UserConnection, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

     Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 50.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {


        Image(painter = painterResource(id = R.drawable.home_screen_image), contentDescription ="clouds",
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)
                .padding(top = 60.dp),
             contentScale = ContentScale.Crop)

            Text(
                text = "Hello!",
                style = MaterialTheme.typography.h1
                    , fontFamily = strikeFontFamily,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "You need to Sign in or Create an account",
                style = MaterialTheme.typography.body2,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

         Button(
             onClick = {

                 /* Sign in */
                 navController.navigate("signIn")

             },
             colors = ButtonDefaults.buttonColors(
                 backgroundColor = Color(0xFF5AE4A8)
             ),

         ) {
             Text(text = "Sign in", style = TextStyle(
                 fontWeight = FontWeight.Bold,
                 fontFamily = poppinsFamily
             ))
         }

            Spacer(modifier = Modifier.height(16.dp))
         // button to sign up over here ...
         Button(
             onClick = { /* Sign Up */

                 navController.navigate("signUp")
             },
             modifier = Modifier.padding(horizontal = 15.dp),
             colors = ButtonDefaults.buttonColors(
                 backgroundColor = Color(0xFF5AE4A8)
             ),
         ) {
             Text(text = "Sign Up",
                 style = TextStyle(
                     fontWeight = FontWeight.Bold,
                     fontFamily = poppinsFamily
                 ))
         }

        }
    }
}


@Composable
fun SignInScreen(viewModel : UserConnection, navController: NavController, viewmodel_note : NoteConnection) {

    val userName = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Box(modifier = Modifier.background(color = Color.White)){
//        Image(painter = painterResource(id = R.drawable.clouds), contentDescription ="clouds", modifier = Modifier.fillMaxSize()
//            , contentScale = ContentScale.Crop)
//        Image(
//            painter = rememberAsyncImagePainter(
//                ImageRequest.Builder(context).data(data = R.drawable.sign_in_image).apply (block = {
//                    size(Size.ORIGINAL)
//                }) .build(), imageLoader = imageLoader
//            ),
//            contentDescription = null,
//            modifier = Modifier.size(150.dp)
//        )

        Column(
            modifier = Modifier
                .fillMaxSize()
//            .background(Color.White)
                .padding(vertical = 50.dp, horizontal = 32.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = R.drawable.sign_in_image).apply (block = {
                        size(Size.ORIGINAL)
                    }) .build(), imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier = Modifier.size(350.dp)
            )
            Text(
                text = "Welcome Back!",
                style = MaterialTheme.typography.h3
                , fontFamily = strikeFontFamily,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
//            Text(
//                text = "Welcome back",
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    color = Color.Black
//                )
//            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                label = { Text("Username") },
                placeholder = { Text("Enter your username") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // write the call to login the particular user ...
                    // first make the request then check whether
                    // do the request over here ...
                    viewModel.signInUser(User(userName.value, password.value))
                    // based on the result switch to the screen..
                },

                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(android.graphics.Color.parseColor("#4319ab"))
                )
            ) {
                Text(
                    text = "Sign in",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = poppinsFamily
                    )
                )
            }

            // logic to check in the launch effect the changes in the sigin in response..

            LaunchedEffect(viewModel.signIn_user_response.value)
            {
                when(viewModel.signIn_user_response.value){
                    "Correct"->{
                        viewModel.userName.value = userName.value
                        viewModel.password.value = password.value
                        viewmodel_note.fetchNoteUser(User(userName.value, password.value))
                        navController.navigate("home")
                    }
                    "Invalid_Password" -> {
//                        navController.navigate("homie")
                        navController.navigate("incorrect")

                    }
                    "User_Dont_exist" -> {
//                        navController.navigate("homie")
                        navController.navigate("exist")
                    }

                }
                Log.d("Ankit_returned", viewModel.signIn_user_response.value)
            }


        }
    }
}

@Composable
fun SignUpScreen(viewModel : UserConnection, navController: NavController) {

//    val viewModel = UserConnection()
    val userName = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Box(
        modifier = Modifier.background(Color(android.graphics.Color.parseColor("#f7efe3")))
    ){


        Column(
            modifier = Modifier
                .fillMaxSize()
//                .background(Color.White)
                .padding(vertical = 50.dp, horizontal = 32.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = R.drawable.create_user).apply (block = {
                        size(Size.ORIGINAL)
                    }) .build(), imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier = Modifier.size(350.dp)
            )

            Text(
                text = "Nice to See you!",
                style = MaterialTheme.typography.h3
                , fontFamily = strikeFontFamily,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Let's do some paper Work ...",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = poppinsFamily
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                label = { Text("Username") },
                placeholder = { Text("Enter your username") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = password.value,
                onValueChange = {

                    password.value = it
                },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                modifier = Modifier.fillMaxWidth(),
//            visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val user_created = User(userName.value, password.value)
                    viewModel.createUser(user_created)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black
                )
            ) {
                Text(
                    text = "Sign up now!",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = poppinsFamily
                    )
                )
            }

            LaunchedEffect(viewModel.Created_user_response.value)
            {
                when(viewModel.Created_user_response.value){
                    "failure"->{
//                        navController.navigate("failed")
                        navController.navigate("exist")

                    }

                    userName.value -> {
                        navController.navigate("signIn")
                    }

                }
                Log.d("Ankit_returned", viewModel.signIn_user_response.value)
            }

        }

    }


}

