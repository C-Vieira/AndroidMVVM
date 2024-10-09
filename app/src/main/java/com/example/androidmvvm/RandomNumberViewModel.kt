package com.example.androidmvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class RandomNumberViewModel : ViewModel() {

    private val _state = MutableStateFlow(0)
    val state: StateFlow<Int> = _state

    fun randomNum() {
        runBlocking {
            _state.emit(Random.nextInt(from = 1, until = 99))
        }
    }
}