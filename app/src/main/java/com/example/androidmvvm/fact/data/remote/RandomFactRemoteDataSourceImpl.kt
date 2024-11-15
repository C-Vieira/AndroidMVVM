package com.example.androidmvvm.fact.data.remote

import com.example.androidmvvm.fact.model.Fact

class RandomFactRemoteDataSourceImpl(private val randomFactService: RandomFactService) :
    RandomFactRemoteDataSource {

    override suspend fun getFact(): Fact {
        val response = randomFactService.getRandomFactData()
        if(response.isSuccessful && response.body() != null){
            return response.body()!!
        }else{
            throw Exception("Api returned with error")
        }
    }
}
