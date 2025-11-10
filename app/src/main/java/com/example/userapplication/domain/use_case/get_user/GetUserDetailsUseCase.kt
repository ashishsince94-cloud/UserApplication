package com.example.userapplication.domain.use_case.get_user

import com.example.userapplication.domain.model.UserDetails
import com.example.userapplication.domain.repository.UserRepository
import com.example.userapplication.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: Int): Flow<Result<UserDetails>> = flow {
        emit(value = userRepository.getUserDetails(userId = userId))
    }.flowOn(context = Dispatchers.IO)
}