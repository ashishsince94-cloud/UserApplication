package com.example.userapplication

sealed class Screen(val route: String) {
    object UserListScreen: Screen(route = "user_list_screen")
    object UserDetailsScreen: Screen(route = "user_details_screen")
}