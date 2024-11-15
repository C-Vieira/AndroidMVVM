package com.example.androidmvvm.fact.data.remote

import com.example.androidmvvm.fact.model.Fact

interface RandomFactRemoteDataSource {
    suspend fun getFact(): Fact
}