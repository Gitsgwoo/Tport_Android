package com.example.tport.network.dto

data class AuthStorageUserDTO(
    val id: Int,
    val name: String,
)

data class LoginRequest(
    val name: String,
    val password: String
)

data class LoginResponse(
    val bearerToken: String
)

data class SignupRequest(
    val name: String,
    val password: String
)

data class SignupResponse(
    val bearerToken: String
)
