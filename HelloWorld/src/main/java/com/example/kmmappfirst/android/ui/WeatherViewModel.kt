package com.example.kmmappfirst.android.ui

import ai.vyro.api.data.repository.WeatherRepo
import ai.vyro.async.entities.AudioType
import ai.vyro.async.entities.Voice
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
  private val weatherRepo: WeatherRepo,
//    private val audio: AudioApi


): ViewModel(){
    private val viewState = MutableStateFlow(WeatherViewState())
    val state = viewState
    val audioType = AudioType.Link("https://youtube.com/shorts/Ust9m3hBX30?si=tnMPsjg-6EQeepP4" ,"maaz") // Replace with your actual file path
    val voice = Voice(3323 ,"" ,"","","","") // Replace with your actual voice ID
    val length = 120

    init {
        processIntent(WeatherActions.LoadWeather)

    }

    fun processIntent(intent: WeatherActions) {
        viewModelScope.launch {
            when (intent) {
                is WeatherActions.LoadWeather -> loadWeather()
            }
        }
    }

    private suspend fun loadWeather() {
        viewState.value = WeatherViewState(isLoading = true)
        try {
//            uploadAudio.invoke(audioType, voice, length)
            val weather = weatherRepo.getWeather()
            viewState.value = WeatherViewState(weather = weather)
            Log.d("WeatherViewModel", "loadWeather: $weather")
        } catch (e: Exception) {
            viewState.value = WeatherViewState(error = e.message ?: "An error occurred")
        }
    }
}