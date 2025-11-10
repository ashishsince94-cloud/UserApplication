package com.example.userapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.userapplication.presentation.ui.theme.MyApplicationTheme
import com.example.userapplication.presentation.user_details.components.UserDetailsScreen
import com.example.userapplication.presentation.user_list.components.UserListScreen
import com.example.userapplication.util.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(paddingValues = innerPadding)
                            .fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.UserListScreen.route
                        ) {
                            composable(route = Screen.UserListScreen.route) {
                                UserListScreen(navController)
                            }
                            composable(route = Screen.UserDetailsScreen.route + "/{${Constant.USER_ID}}") {
                                UserDetailsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}