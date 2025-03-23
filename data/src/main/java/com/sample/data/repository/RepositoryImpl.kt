package com.sample.data.repository

import com.sample.data.mapper.toUsers
import com.sample.data.network.APIService
import com.sample.domain.model.User
import com.sample.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor (var apiService: APIService) : UsersRepository {
    override suspend fun getUsersList(category: String, apikey: String): Flow<Result<List<User>>> = flow {
        val response= apiService.getUsers(category,apikey)
        emit(Result.success(response.sources.map {it.toUsers()  }))
    }.catch {e->emit(Result.failure(Exception(e.message)))
    }
}