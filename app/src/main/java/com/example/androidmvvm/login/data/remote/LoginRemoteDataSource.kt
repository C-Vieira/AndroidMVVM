package com.example.androidmvvm.login.data.remote

import com.example.androidmvvm.login.model.UserAuth

interface LoginRemoteDataSource {
    suspend fun createAccount(email: String, password: String): UserAuth
    suspend fun signIn(email: String, password: String): UserAuth
    suspend fun recover(email: String)
    fun isSessionValid(): Boolean
}