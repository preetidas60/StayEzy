package com.example.stayzy.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.stayzy.AuthViewModel

@Composable
fun Explore(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel){
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ){ paddingValues ->
        
        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            Text(text = "Explore")
        }
        
    }
}