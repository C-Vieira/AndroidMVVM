package com.example.androidmvvm.login.di

import com.example.androidmvvm.login.presentation.LoginViewModel
import com.example.androidmvvm.login.data.LoginRepository
import com.example.androidmvvm.login.data.LoginRepositoryImpl
import com.example.androidmvvm.login.data.remote.LoginRemoteDataSource
import com.example.androidmvvm.login.data.remote.LoginRemoteDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    factory<LoginRemoteDataSource> {
        LoginRemoteDataSourceImpl(firebaseAuth = FirebaseAuth.getInstance())
    }

    factory<LoginRepository> {
        LoginRepositoryImpl(loginRemoteDataSource = get())
    }

    viewModel {
        LoginViewModel(loginRepository = get())
    }
}