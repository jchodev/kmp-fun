package org.jerry.kmp.di

import org.koin.dsl.module
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.jerry.kmp.contants.BASE_URL

private const val TIMEOUT = 30000L
val networkModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Application.Json
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = TIMEOUT
                connectTimeoutMillis = TIMEOUT
                requestTimeoutMillis = TIMEOUT
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            defaultRequest {
                url(BASE_URL)
            }
        }
    }
}