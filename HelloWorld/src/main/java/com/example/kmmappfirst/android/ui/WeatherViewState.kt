package com.example.kmmappfirst.android.ui

import ai.vyro.api.data.model.Weather


data class WeatherViewState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String = ""
)