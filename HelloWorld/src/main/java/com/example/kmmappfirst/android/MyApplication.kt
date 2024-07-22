package com.example.kmmappfirst.android

import ai.vyro.async.entities.annotations.InternalVoiceAiApi
import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmmappfirst.startSharedKoin
import org.koin.android.ext.koin.androidContext
import viewModelModule


class MyApplication : Application() {
    @OptIn(InternalVoiceAiApi::class)
    override fun onCreate() {
        super.onCreate()
        startSharedKoin {
            androidContext(this@MyApplication)
            modules(
                viewModelModule,
            )
        }
    }
}