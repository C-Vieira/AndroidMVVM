package com.example.androidmvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        retrofit.getRandomFact().enqueue(object: Callback<Fact> {
            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                if(response.isSuccessful){
                    CoroutineScope(Dispatchers.IO).launch {
                        _state.emit(response.body()?.text ?: "Whoops, something went wrong...")
                    }
                }
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    _state.emit("Whoops, something went wrong...")
                }
            }

        })
    }
}