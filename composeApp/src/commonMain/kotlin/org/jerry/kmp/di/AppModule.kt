package org.jerry.kmp.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            apiModule, repositoryModule, viewModelModule, networkModule, platformModule()
        )
    }


//val appModule = listOf(apiModule, repositoryModule, viewModelModule, networkModule, platformModule() )

expect fun platformModule(): Module
