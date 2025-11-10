package com.example.userapplication.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapplication.domain.use_case.get_users.GetUsersUseCase
import com.example.userapplication.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * ViewModel responsible for fetching and exposing the list of users to the UI.
 *
 * It listens to the [GetUsersUseCase] Flow and updates [UserListState]
 * with loading, success, and error states.
 */
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _state = mutableStateOf(value = UserListState())
    val state: State<UserListState> = _state

    init {
        getUserList()
    }

    private fun getUserList() {
        getUsersUseCase().onStart {
            _state.value = UserListState(isLoading = true)
        }.onEach { result ->
            when (result) {
                Result.Loading -> {
                    _state.value = UserListState(isLoading = true)
                }

                is Result.Success -> {
                    _state.value = UserListState(users = result.data ?: emptyList())
                }

                is Result.Error -> {
                    _state.value =
                        UserListState(error = result.message ?: "An unexpected error occurred.")
                }
            }
        }.launchIn(scope = viewModelScope)
    }
}