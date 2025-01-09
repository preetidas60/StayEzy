package com.example.stayzy.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.room.util.query
import com.example.stayzy.AuthViewModel
import com.example.stayzy.R
import com.example.stayzy.ui.theme.Purple80
import com.example.stayzy.ui.theme.StayzyTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {

    var query by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Image(painter = painterResource(R.drawable.user__1_),
                            contentDescription = "user profile",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Hello, user!",
                            fontSize = 20.sp
                            )
                    }
                        },
                actions = {

                    // New Image Button
                    IconButton(onClick = {
                        navController.navigate("Chat")
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.bell), // Replace with your actual drawable resource
                            contentDescription = "Top Right Icon",
                            modifier = Modifier.size(24.dp) // Adjust size as needed
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFFFFFFF), // Same color for Top Bar
                    titleContentColor = Color(0xFF000000)
                )
            )
        },

        bottomBar = { BottomNavigationBar(navController) },

        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                // Attendance Chart
                Text(
                    text = "Where do you",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 35.sp
                )
                Text(
                    text = "want to explore today?",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 35.sp
                )
            }

            item {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Search destination", color = Color.Gray) },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp)),
                            colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFE9ECEC),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()

                ){
                    Text(text = "Choose Category",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(text = "See All",
                        modifier = Modifier
                            .clickable {

                            }
                    )
                }
            }

            item {
                LazyRow {
                    item {
                        // Attendance Automation Card
                        FeatureCard(
                            title = "Beach",
                            iconRes = R.drawable.beach, // Placeholder for Alerts Icon
                            onClick = {
                                // Navigate to Alerts Screen
                                navController.navigate("Library")
                            }
                        )
                    }

                    item {
                        // Attendance Automation Card
                        FeatureCard(
                            title = "Mountain",
                            iconRes = R.drawable.mountain, // Placeholder for Alerts Icon
                            onClick = {
                                // Navigate to Alerts Screen
                                navController.navigate("Library")
                            }
                        )
                    }

                    item {
                        // Attendance Automation Card
                        FeatureCard(
                            title = "Forest",
                            iconRes = R.drawable.forest, // Placeholder for Alerts Icon
                            onClick = {
                                // Navigate to Alerts Screen
                                navController.navigate("Library")
                            }
                        )
                    }
                }
            }
            
            item { 
                Text(text = "Favourite Place",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(10.dp)
                    )
            }
        }
    }
}



@Composable
fun FeatureCard(title: String,
                iconRes: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(40.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon for the Feature
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "$title Icon",
                modifier = Modifier.size(50.dp)
            )

            // Feature Title
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 2.dp, end = 4.dp)
            )
        }

    }
}



@Composable
fun BottomNavigationBar(navController: NavController) {
    // Observe the backstack entry as state to know the current screen
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .height(100.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)) // Rounded upper corners
//            .background(Color.White) // Optional background for visibility
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp),
                    tint = (if (currentRoute == "Home") Purple80 else Color.Gray) as Color // Check the current route
                )
            },
            label = { Text(text = "Home") },
            selected = currentRoute == "Home", // Mark as selected if route is "home"
            onClick = {
                navController.navigate("Home") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.compass),
                    contentDescription = "Explore",
                    modifier = Modifier.size(18.dp),
                    tint = if (currentRoute == "Explore") Purple80 else Color.Gray
                )
            },
            label = { Text(text = "Explore") },
            selected = currentRoute == "Explore",
            onClick = {
                navController.navigate("Explore") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.chat__3_),
                    contentDescription = "Search",
                    modifier = Modifier.size(18.dp),
                    tint = if (currentRoute == "Search") Purple80 else Color.Gray
                )
            },
            label = { Text(text = "Message") },
            selected = currentRoute == "Message",
            onClick = {
                navController.navigate("Message") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                unselectedIconColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.user__1_),
                    contentDescription = "Profile",
                    modifier = Modifier.size(18.dp),
                    tint = if (currentRoute == "Profile") Purple80 else Color.Gray
                )
            },
            label = { Text(text = "Profile") },
            selected = currentRoute == "Profile",
            onClick = {
                navController.navigate("Profile") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple80,
                unselectedIconColor = Color.Gray
            )
        )
    }
}