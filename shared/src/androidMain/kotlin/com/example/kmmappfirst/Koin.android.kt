package com.example.kmmappfirst

import ai.vyro.integrity.IntegrityProvider
import ai.vyro.integrity.instance

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal val android = module {
    single { IntegrityProvider.instance(get(),26780473292,get()) }
}

fun startSharedKoin(action:KoinApplication.() -> Unit )  {
    startKoin {
        modules(dependenciesModule, android)
        action()
    }
}


actual fun platform(): String = "android"