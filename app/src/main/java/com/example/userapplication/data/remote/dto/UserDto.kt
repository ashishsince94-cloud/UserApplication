package com.example.userapplication.data.remote.dto

import com.example.userapplication.domain.model.User
import com.example.userapplication.domain.model.UserDetails
import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) representing user data received from the API.
 *
 */
data class UserDto(
    val address: String,
    val company: String,
    val country: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val photo: String,
    val state: String,
    @SerializedName(value = "username")
    val userName: String,
    val zip: String
)

/**
 * Maps [UserDto] to a lightweight [User] domain model.
 * Use this when only basic user information is required.
 */
fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name
    )
}

/**
 * Maps [UserDto] to a detailed [UserDetails] domain model.
 * Use this for detailed user profile information.
 */
fun UserDto.toUserDetails(): UserDetails {
    return UserDetails(
        id = id,
        name = name,
        email = email,
        userName = userName,
        address = address,
        company = company,
        country = country,
        phone = phone,
        photo = photo,
        state = state,
        zip = zip,
    )
}