package com.example.androidmvvm.fact.data.remote

import com.example.androidmvvm.fact.model.Fact
import retrofit2.Response
import retrofit2.http.GET

interface RandomFactService {
    @GET("/api/v2/facts/random")
    suspend fun getRandomFactData(): Response<Fact>
}