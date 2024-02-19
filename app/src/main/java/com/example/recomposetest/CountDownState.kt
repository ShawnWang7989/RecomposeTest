package com.example.recomposetest

sealed interface CountDownState {
    data object StartButton : CountDownState
    data class CountDown(val count: Int) : CountDownState
}