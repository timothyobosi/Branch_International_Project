package com.industry.branchinternationalproject

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("api/messages")
    fun createMessage(@Body requestBody: Map<String, String>): Call<MessageResponse>

    @GET("api/message_threads")
    fun getAllMessageThreads(): Call<List<MessageThread>>

    @POST("login") // Define the login endpoint
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
