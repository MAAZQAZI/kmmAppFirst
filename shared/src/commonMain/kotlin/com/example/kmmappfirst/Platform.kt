package com.example.kmmappfirst

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
