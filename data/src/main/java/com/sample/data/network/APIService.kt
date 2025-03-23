package com.sample.data.network

import com.sample.data.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("top-headlines/sources")
    suspend fun getUsers(@Query("category") category: String,@Query("apiKey")
    apiKey:String): ResponseData

}