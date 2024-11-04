package com.example.androidmvvm

interface FactRepository {
    suspend fun getRandomFact(): String
}