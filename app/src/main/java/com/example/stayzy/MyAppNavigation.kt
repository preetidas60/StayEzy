package com.example.stayzy

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stayzy.pages.Explore
import com.example.stayzy.pages.Home
import com.example.stayzy.pages.SignupPage
import com.example.stayzy.pages.LoginPage
import com.example.stayzy.pages.Message
import com.example.stayzy.pages.Profile

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    // Determine start destination based on authentication status
    val startDestination = remember(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> "Home"
            else -> "Login"
        }
    }

    // Observe authState and navigate accordingly
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate("Home") {
                    popUpTo("Login") { inclusive = true } // Prevent back navigation to Login
                }
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState.value as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit
        }
    }

    // Navigation host
    NavHost(navController = navController, startDestination = startDestination, builder = {
        composable("Login") {
            LoginPage(modifier, navController, authViewModel)
        }

        composable("Signup") {
            SignupPage(modifier, navController, authViewModel)
        }

        composable("Home") {
            Home(modifier, navController, authViewModel)
        }

        composable("Profile") {
            Profile(modifier, navController, authViewModel)
        }

        composable("Explore") {
            Explore(modifier, navController, authViewModel)
        }

        composable("Message") {
            Message(modifier, navController, authViewModel)
        }


        composable("Message") {
            Message(modifier, navController, authViewModel)
        }
    })
}
