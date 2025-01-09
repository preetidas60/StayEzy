package com.example.stayzy.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stayzy.AuthState
import com.example.stayzy.AuthViewModel
import com.example.stayzy.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel){
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember { mutableStateOf(false) }

    var isFocused by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    val authState by authViewModel.authState.observeAsState(initial = AuthState.Unauthenticated)


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(
            painter = painterResource(id = R.drawable.login), contentDescription = "Login image",
            modifier = Modifier.size(200.dp)
        )
        Text(text = "Welcome", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Create your new Account")

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = {
            Text(text = "Email address")
        },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF91F795),
                unfocusedBorderColor = Color(0xFF91F795),
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (isFocused) { // Show eye icon only when the field is focused
                    val image = if (passwordVisible)
                        painterResource(id = R.drawable.view) // Replace with your "eye-open" drawable resource
                    else
                        painterResource(id = R.drawable.hide) // Replace with your "eye-closed" drawable resource

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = image, contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            modifier = Modifier.size(20.dp))
                    }
                }
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF91F795),
                unfocusedBorderColor = Color(0xFF91F795),
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )


        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                authViewModel.signup(email, password) // Trigger the signup process
                authViewModel.authState.observeForever { authState ->
                    when (authState) {
                        is AuthState.Authenticated -> {
                            navController.navigate("Home") // Navigate only if authenticated
                        }
                        is AuthState.Error -> {
                            // Show error message (e.g., using Toast or Snackbar)
                            println("Sign-up failed: ${authState.message}") // Replace with UI error handling
                        }
                        else -> Unit // Handle other states if necessary
                    }
                }
            },
                    colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF48DB4F),
            ),
            modifier = Modifier
                .size(width = 275.dp, height = 45.dp) // Adjust size as needed
        ) {
            Text(text = "Signup",
                fontSize = 18.sp)
        }



        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Or sign up using")



        Row(
            modifier = Modifier
                .padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),

            ) {
            Image(painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google icon",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                    }
            )

            Image(painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook icon",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                    }
            )

            Image(painter = painterResource(id = R.drawable.twitter),
                contentDescription = "Twitter icon",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                    }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(modifier = Modifier,
            horizontalArrangement = Arrangement.Center){
            Text(text = "Already have an account?")


            Text(text = " Login", modifier = Modifier
                .clickable {
                    navController.navigate("Login")
                },
                color = colorResource(id = R.color.purple_700)
            )
        }

    }
}