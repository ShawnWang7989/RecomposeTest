package com.example.recomposetest

data class UIState(
    val countDownState: CountDownState = CountDownState.StartButton,
    val titleState: TitleState = TitleState.CoolTitle("Very Cool")
)