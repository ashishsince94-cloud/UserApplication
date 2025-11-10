package com.example.userapplication.presentation.user_list

import com.example.userapplication.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)