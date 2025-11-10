package com.example.userapplication.data.remote

import com.example.userapplication.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit API service that defines endpoints related to user operations.
 * Uses coroutines (suspend functions) for asynchronous network calls.
 */
interface ApiService {

    /**
     * Retrieves the list of all users from the server.
     *
     * @return A [List] containing a list of [UserDto] objects.
     */
    @GET("/users")
    suspend fun getUsers(): List<UserDto>

    /**
     * Retrieves detailed information for a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return A single [UserDto] object.
     */
    @GET("/users/{userId}")
    suspend fun getUserDetails(@Path(value = "userId") userId: Int): UserDto

}