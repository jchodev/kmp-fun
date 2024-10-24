package org.jerry.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform