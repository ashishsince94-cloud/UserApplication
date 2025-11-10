package com.example.userapplication.domain.use_case.get_users

import com.example.userapplication.domain.model.User
import com.example.userapplication.domain.repository.UserRepository
import com.example.userapplication.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<Result<List<User>>> = flow {
        emit(value = userRepository.getUsers())
    }.flowOn(context = Dispatchers.IO)
}