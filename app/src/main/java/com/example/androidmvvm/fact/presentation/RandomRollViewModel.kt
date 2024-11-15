package com.example.androidmvvm.fact.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvvm.fact.data.RandomFactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RandomRollViewModel(private val randomFactRepository: RandomFactRepository): ViewModel() {

    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state.asStateFlow()

    fun roll() {
        viewModelScope.launch(Dispatchers.IO) {
            val fact = randomFactRepository.getRandomFact()
            _state.update { fact.text }
        }
    }
}