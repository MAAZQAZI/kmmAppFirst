package com.example.kmmappfirst

import ai.vyro.async.asyncModules
import ai.vyro.async.entities.errors.GenerationError
import ai.vyro.async.entities.network.Endpoints
import ai.vyro.integrity.IntegrityProvider
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapError
import org.koin.core.context.startKoin
import org.koin.core.definition.Definition
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platform(): String

val endpoints: Definition<Endpoints>  = {
    val integrity = get<IntegrityProvider>()
    Endpoints(
        base = "https://musicly.vyro.ai/latest/android"

    ){
        integrity.get(expired = it).mapError { GenerationError.Local(it) }
    }
}

internal val dependenciesModule = module {
    includes(
    asyncModules(endpoints)
    )
}




fun initKoin(config:KoinAppDeclaration?=null){
    startKoin {
        config?.invoke(this)
        modules(dependenciesModule)
    }
}