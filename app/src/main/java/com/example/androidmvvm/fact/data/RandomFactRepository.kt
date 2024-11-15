package com.example.androidmvvm.fact.data

import com.example.androidmvvm.fact.model.Fact

interface RandomFactRepository {
    suspend fun getRandomFact(): Fact
}