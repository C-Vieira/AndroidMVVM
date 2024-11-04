package com.example.androidmvvm

class FactRepositoryImpl(private val dataSource: RandomFactDataSource): FactRepository {

    override suspend fun getRandomFact(): String {
        return dataSource.getDataFromAPI()
    }
}