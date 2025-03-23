package com.sample.domain.usecase

import com.sample.domain.model.User
import com.sample.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetListUsersUseCase @Inject constructor(var users: UsersRepository) {
    suspend fun getListUsers(category:String,apiKey: String): Flow<Result<List<User>>> {
        return users.getUsersList(category,apiKey)
    }
}