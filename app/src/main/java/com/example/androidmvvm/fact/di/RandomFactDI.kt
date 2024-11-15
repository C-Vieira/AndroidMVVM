package com.example.androidmvvm.fact.di

import com.example.androidmvvm.fact.presentation.RandomRollViewModel
import com.example.androidmvvm.fact.data.RandomFactRepository
import com.example.androidmvvm.fact.data.RandomFactRepositoryImpl
import com.example.androidmvvm.fact.data.remote.RandomFactRemoteDataSource
import com.example.androidmvvm.fact.data.remote.RandomFactRemoteDataSourceImpl
import com.example.androidmvvm.fact.data.remote.RandomFactService

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val factModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://uselessfacts.jsph.pl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<RandomFactService> {
        get<Retrofit>().create(RandomFactService::class.java)
    }

    factory<RandomFactRemoteDataSource> {
        RandomFactRemoteDataSourceImpl(randomFactService = get())
    }

    factory<RandomFactRepository> {
        RandomFactRepositoryImpl(randomFactRemoteDataSource = get())
    }

    viewModel<RandomRollViewModel> {
        RandomRollViewModel(randomFactRepository = get())
    }
}