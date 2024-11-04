package com.example.androidmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RandomRollViewModel(private val repository: FactRepository): ViewModel() {

    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state.asStateFlow()

    fun roll() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { repository.getRandomFact() }
        }
    }
}