package com.example.recomposetest

sealed interface TitleState {
    data object NoTitle : TitleState
    data class CoolTitle(val title: String) : TitleState
}