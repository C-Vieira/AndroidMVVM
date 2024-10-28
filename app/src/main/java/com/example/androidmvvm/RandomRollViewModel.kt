package com.example.androidmvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomRollViewModel : ViewModel() {

    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://uselessfacts.jsph.pl/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RandomFactService::class.java)

    fun roll() {
        /*runBlocking {
            _state.emit(Random.nextInt(from = 1, until = 99).toString())
        }*/

        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getRandomFact()
            if (response.isSuccessful) {
                launch(Dispatchers.Main) {
                    if (!response.body().isNullOrEmpty()) {
                        _state.emit(response.body().toString())
                    }
                }
            }
        }
    }
}