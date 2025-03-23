package com.sample.domain.repository

import com.sample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsersList(category:String,apikey:String):Flow<Result<List<User>>>
}