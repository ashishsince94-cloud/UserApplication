package com.example.userapplication.domain.repository

import com.example.userapplication.domain.model.User
import com.example.userapplication.domain.model.UserDetails
import com.example.userapplication.util.Result

interface UserRepository {

    /**
     * Fetches a list of users from the remote API and maps each to a [User] domain model.
     *
     * @return [Result] containing either the list of users or an error message.
     */
    suspend fun getUsers(): Result<List<User>>

    /**
     * Fetches detailed user information from the remote API for the given [userId].
     *
     * @param userId The unique identifier of the user.
     * @return [Result] containing either user details or an error message.
     */
    suspend fun getUserDetails(userId: Int): Result<UserDetails>
}