package com.example.androidmvvm

import retrofit2.Call
import retrofit2.http.GET

interface RandomFactService {
    @GET("/api/v2/facts/random")
    fun getRandomFact(): Call<Fact>
}