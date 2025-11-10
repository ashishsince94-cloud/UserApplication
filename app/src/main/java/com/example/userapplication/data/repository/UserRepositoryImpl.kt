package com.example.userapplication.data.repository

import com.example.userapplication.data.remote.ApiService
import com.example.userapplication.data.remote.dto.toUser
import com.example.userapplication.data.remote.dto.toUserDetails
import com.example.userapplication.domain.model.User
import com.example.userapplication.domain.model.UserDetails
import com.example.userapplication.domain.repository.UserRepository
import com.example.userapplication.util.Result
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Implementation of [UserRepository] that interacts with the remote API.
 * Handles API calls safely and maps DTOs to domain models.
 */
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return safeCall {
            apiService.getUsers().map {
                it.toUser()
            }
        }
    }

    override suspend fun getUserDetails(userId: Int): Result<UserDetails> {
        return safeCall {
            apiService.getUserDetails(userId = userId).toUserDetails()
        }
    }

}

/**
 * Executes a suspendable network call safely and wraps it in a [Result].
 * Provides consistent error handling for IO, HTTP, and generic exceptions.
 */
private inline fun <T> safeCall(action: () -> T): Result<T> {
    return try {
        Result.Success(data = action())
    } catch (e: IOException) {
        Result.Error(message = "Couldn't reach server. Check your internet connection. ${e.localizedMessage}")
    } catch (e: HttpException) {
        Result.Error(message = e.localizedMessage ?: "An unexpected error occurred.")
    } catch (e: Exception) {
        Result.Error(
            e.localizedMessage ?: "An unexpected error occurred. Please check your internet."
        )
    }
}