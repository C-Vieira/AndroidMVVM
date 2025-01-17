package com.example.androidmvvm.login.data

import com.example.androidmvvm.login.model.UserAuth

interface LoginRepository {
    fun isSessionValid(): Boolean
    suspend fun createAccount(email: String, password: String): UserAuth
    suspend fun login(email: String, password: String): UserAuth
    suspend fun recover(email: String)
}