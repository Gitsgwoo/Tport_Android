package com.example.tport.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tport.network.RestService
import com.example.tport.network.dto.AuthStorageUserDTO
import com.example.tport.network.dto.LoginRequest
import com.example.tport.network.dto.SignupRequest
import com.example.tport.util.AuthStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(
    private val restService: RestService,
    private val authStorage: AuthStorage,
): ViewModel() {

    private val _userTags = MutableStateFlow<MutableList<String>>(mutableListOf())
    val userTags: StateFlow<List<String>> = _userTags
    suspend fun login(id: String, password: String) {
        try {
            val response = restService.login(LoginRequest(id, password))
            authStorage.setAuthInfo(
                response.bearerToken,
                AuthStorageUserDTO(1, "건우")
            )
        } catch (e: Exception) {
            Log.d("Error", "Error is occurred. Error: $e")
        }
    }

    suspend fun signup(name: String, password: String) {
        try {
            restService.signup(SignupRequest(name, password))
        } catch (e: Exception) {
            Log.d("Error", "Error is occurred")
        }
    }

    //    suspend fun signup(id: String, password: String, nickname: String) {
//        try {
//            restService.signup(SignupRequest(id, password, nickname))
//        } catch (e: Exception) {
//            Log.d("Error", "Error is occurred")
//        }
//    }
/*
    suspend fun logout() {
        try {
            restService.logout()
        } catch (e: Exception) {
            Log.d("Error", "Error is occurred")
        }
    }
*/
    fun addTags(tag: String) {
        _userTags.value.add(tag)
    }
}