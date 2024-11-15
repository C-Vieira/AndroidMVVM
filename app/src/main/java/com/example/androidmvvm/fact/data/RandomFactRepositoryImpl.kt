package com.example.androidmvvm.fact.data

import com.example.androidmvvm.fact.data.remote.RandomFactRemoteDataSource
import com.example.androidmvvm.fact.model.Fact

class RandomFactRepositoryImpl(private val randomFactRemoteDataSource: RandomFactRemoteDataSource):
    RandomFactRepository {

    override suspend fun getRandomFact(): Fact =
        runCatching {
            randomFactRemoteDataSource.getFact()
        }.getOrDefault(
            Fact(
                id = "",
                text = "Whoops, something went wrong...",
                source = "fallback",
                sourceUrl = "",
                language = "",
                permalink = ""
            )
        )
}