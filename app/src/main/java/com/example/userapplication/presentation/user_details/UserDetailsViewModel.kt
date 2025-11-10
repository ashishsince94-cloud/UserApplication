package com.example.userapplication.presentation.user_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapplication.domain.use_case.get_user.GetUserDetailsUseCase
import com.example.userapplication.presentation.user_list.UserListState
import com.example.userapplication.util.Constant
import com.example.userapplication.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * ViewModel responsible for fetching and exposing user details to the UI.
 *
 * It reacts to the [GetUserDetailsUseCase] flow and updates [UserDetailsState]
 * accordingly, handling loading, success, and error states.
 *
 * @property getUserDetailsUseCase The use case that fetches user details.
 * @property savedStateHandle Provides navigation arguments such as userId.
 */
@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(value = UserDetailsState())
    val state: State<UserDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constant.USER_ID)?.let { userId ->
            getUserDetails(userId = userId.toInt())
        }
    }

    private fun getUserDetails(userId: Int) {
        getUserDetailsUseCase(userId = userId).onStart {
            _state.value = UserDetailsState(isLoading = true)
        }.onEach { result ->
            when (result) {
                Result.Loading -> {
                    _state.value = UserDetailsState(isLoading = true)
                }

                is Result.Success -> {
                    _state.value = UserDetailsState(user = result.data)
                }

                is Result.Error -> {
                    UserListState(error = result.message ?: "An unexpected error occurred.")
                }
            }
        }.launchIn(scope = viewModelScope)
    }
}