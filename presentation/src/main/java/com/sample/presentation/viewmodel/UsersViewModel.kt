package com.sample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.domain.model.User
import com.sample.domain.usecase.GetListUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UsersViewModel @Inject constructor(private val usersUseCase: GetListUsersUseCase) :
    ViewModel() {
    private val _newList = MutableStateFlow<List<User>>(emptyList())
    var newsList = _newList.asStateFlow()
    private val _errorFlow = MutableStateFlow<String>("")
    var errorData: StateFlow<String> = _errorFlow.asStateFlow()
    fun fetchData(category: String, apiKey: String) {
        viewModelScope.launch {
            usersUseCase.getListUsers(category, apiKey).collectLatest { result ->
                result.onSuccess {
                    _newList.value = it
                    _errorFlow.value = ""
                }
                result.onFailure {
                    _newList.value = emptyList()
                    _errorFlow.value = it.message.toString()
            }
            }
        }

    }



}