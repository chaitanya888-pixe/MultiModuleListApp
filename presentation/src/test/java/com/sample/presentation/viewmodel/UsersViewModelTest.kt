package com.sample.presentation.viewmodel

import app.cash.turbine.test
import com.sample.domain.model.User
import com.sample.domain.usecase.GetListUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {
    private lateinit var viewModel: UsersViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var mockUsersUseCase: GetListUsersUseCase


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)

        viewModel = UsersViewModel(mockUsersUseCase)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun fetchDataSuccess() = runTest {
        val users = listOf(
            User(
                "chaitanya", "USA", "name", "1",
                "", "", ""
            )
        )

        val flow = flowOf(Result.success(users))

        `when`(
            mockUsersUseCase.getListUsers(
                "business",
                "538c416021af406d9d75dc4c04c93267"
            )
        ).thenReturn(flow)
        viewModel.fetchData("business", "538c416021af406d9d75dc4c04c93267")
        advanceUntilIdle()
        viewModel.newsList.test {
            assertEquals(users, awaitItem())
        }
        viewModel.errorData.test {
            assertEquals("", awaitItem())
        }
    }

    @Test
    fun checkTheCoroutines() = runTest {
        var errormsg = "Msg"
        var flowData = flowOf(Result.failure<List<User>>(Exception(errormsg)))
        `when`(
            mockUsersUseCase.getListUsers(
                "business",
                "538c416021af406d9d75dc4c04c93267"
            )
        ).thenReturn(flowData)

        viewModel.fetchData("business", "538c416021af406d9d75dc4c04c93267")
        advanceUntilIdle()
        viewModel.newsList.test {
            assertEquals(emptyList<User>(), awaitItem()) // Should emit an empty list
        }

        viewModel.errorData.test {
            assertEquals(errormsg, awaitItem()) // Should emit the error message
        }

    }
}