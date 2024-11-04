package com.example.androidmvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomFactDataSource {
    private val api = Retrofit.Builder()
        .baseUrl("https://uselessfacts.jsph.pl/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RandomFactService::class.java)

    suspend fun getDataFromAPI(): String{
        val response = api.getRandomFactData()
        if(response.isSuccessful){
            val fact = response.body()
            return fact?.text ?: "Whoops, Something went wrong..."
        }else{
            return "Whoops, Something went wrong..."
        }
    }
}
