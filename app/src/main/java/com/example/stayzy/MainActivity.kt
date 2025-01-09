package com.example.stayzy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stayzy.ui.theme.StayzyTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val authViewModel: AuthViewModel by viewModels()


        setContent {
            StayzyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
//                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    MyAppNavigation(modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel)
//                    LoginPage(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding),
////                        navController = navController,
//                        authViewModel = authViewModel
//                    )
                }

            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun defaultPreview(){
    StayzyTheme {
        MyAppNavigation(authViewModel = AuthViewModel())
    }
}