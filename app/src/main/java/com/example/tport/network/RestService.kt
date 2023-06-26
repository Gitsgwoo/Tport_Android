package com.example.tport.network

import com.example.tport.network.dto.LoginRequest
import com.example.tport.network.dto.LoginResponse
import com.example.tport.network.dto.SignupRequest
import com.example.tport.network.dto.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RestService {
    @POST("api/v1/signup")
    suspend fun signup(@Body request: SignupRequest): SignupResponse

    @POST("api/v1/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}