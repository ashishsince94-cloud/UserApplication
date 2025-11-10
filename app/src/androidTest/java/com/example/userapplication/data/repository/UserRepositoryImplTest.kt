package com.example.userapplication.data.repository

import com.example.userapplication.data.remote.ApiService
import com.example.userapplication.data.remote.dto.UserDto
import com.example.userapplication.util.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        apiService = mockk()
        repository = UserRepositoryImpl(apiService)
    }

    @Test
    fun getUsers_returns_Success_when_API_call_is_successful() = runTest {
        // Given
        val mockUsers = listOf(
            UserDto(
                id = 1,
                name = "Jensen Donnelly",
                company = "Gutmann, Gislason and Sauer",
                userName = "Lauren11",
                email = "Osbaldo.Conroy@yahoo.com",
                address = "83074 Kenyatta Land",
                zip = "02513",
                state = "Louisiana",
                country = "Nepal",
                phone = "(514) 977-5319",
                photo = "https://json-server.dev/ai-profiles/27.png"
            )
        )

        coEvery { apiService.getUsers() } returns mockUsers

        // When
        val result = repository.getUsers()

        // Then
        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals(1, data?.size)
        assertEquals("Jensen Donnelly", data?.get(0)?.name)
    }

    @Test
    fun getUserDetails_returns_Success_when_API_call_is_successful() = runTest {
        // Given
        val userId = 1
        val mockUserDto = UserDto(
            id = 1,
            name = "Hortense Kovacek",
            company = "Nicolas - Stokes",
            userName = "Dianna65",
            email = "Buddy.Renner87@hotmail.com",
            address = "909 Everette Cape",
            zip = "20793-9095",
            state = "South Carolina",
            country = "Saint Kitts and Nevis",
            phone = "(692) 914-4540 x188",
            photo = "https://json-server.dev/ai-profiles/4.png"
        )

        coEvery { apiService.getUserDetails(userId) } returns mockUserDto

        // When
        val result = repository.getUserDetails(userId)

        // Then
        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals("Hortense Kovacek", data?.name)
    }

    @Test
    fun getUsers_returns_Error_when_IOException_is_thrown() = runTest {
        // Given
        coEvery { apiService.getUsers() } throws IOException("Network error")

        // When
        val result = repository.getUsers()

        // Then
        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).message?.contains("Check your internet") ?: false)
    }

    @Test
    fun getUserDetails_returns_Error_when_HttpException_is_thrown() = runTest {
        // Given
        val userId = 1
        val response = Response.error<UserDto>(
            404,
            ResponseBody.create("application/json".toMediaTypeOrNull(), "{}")
        )
        coEvery { apiService.getUserDetails(userId) } throws HttpException(response)

        // When
        val result = repository.getUserDetails(userId)

        // Then
        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).message?.contains("unexpected") ?: false)
    }

    @Test
    fun getUsers_returns_Error_when_unknown_Exception_is_thrown() = runTest {
        // Given
        coEvery { apiService.getUsers() } throws RuntimeException("Something went wrong")

        // When
        val result = repository.getUsers()

        // Then
        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).message?.contains("unexpected") ?: false)
    }
}