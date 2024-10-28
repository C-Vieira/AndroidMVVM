package com.example.androidmvvm

import retrofit2.http.GET

interface RandomFactService {
    @GET("/api/v2/facts/random")
    suspend fun getRandomFact(): retrofit2.Response<String>
}