package com.example.userapplication.presentation.user_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.userapplication.Screen
import com.example.userapplication.presentation.user_list.UserListViewModel

@Composable
fun UserListScreen(
    navController: NavController,
    userListViewModel: UserListViewModel = hiltViewModel()
) {
    val userListScreenState = userListViewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = userListScreenState.users) { user ->
                UserListItem(
                    user = user,
                    onClick = {
                        navController.navigate(route = Screen.UserDetailsScreen.route + "/${user.id}")
                    }
                )
            }
        }
        if (userListScreenState.error.isNotBlank()) {
            Text(
                text = userListScreenState.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp)
                    .align(alignment = Alignment.Center)
            )
        }
        if (userListScreenState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}