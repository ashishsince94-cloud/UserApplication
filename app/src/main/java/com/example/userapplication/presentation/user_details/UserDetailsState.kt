package com.example.userapplication.presentation.user_details

import com.example.userapplication.domain.model.User
import com.example.userapplication.domain.model.UserDetails

data class UserDetailsState(
    val isLoading: Boolean = false,
    val user: UserDetails? = null,
    val error: String = ""
)