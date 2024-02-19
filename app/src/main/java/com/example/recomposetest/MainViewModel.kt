package com.example.recomposetest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun buttonClick() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(titleState = TitleState.NoTitle)
            }
            repeat(5) { count ->
                _uiState.update {
                    it.copy(countDownState = CountDownState.CountDown(5 - count))
                }
                delay(1000L)
            }
            _uiState.update {
                it.copy(
                    countDownState = CountDownState.StartButton,
                    titleState = TitleState.CoolTitle("Very Cool")
                )
            }
        }
    }
}