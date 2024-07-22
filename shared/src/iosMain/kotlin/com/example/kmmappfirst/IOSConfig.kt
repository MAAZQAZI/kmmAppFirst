package com.example.kmmappfirst

import org.koin.core.context.startKoin
import org.koin.dsl.module

class IOSConfig {

    fun koin(){
        startKoin{
            modules(dependenciesModule, ios)
        }
    }

    fun  build() = Unit
}

internal val dependenciesModule = module {
    includes(

    )
}

