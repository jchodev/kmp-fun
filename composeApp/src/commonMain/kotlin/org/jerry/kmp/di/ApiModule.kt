package org.jerry.kmp.di

import org.jerry.kmp.network.PodcastApi
import org.koin.dsl.module

val apiModule = module {
    single { PodcastApi(get()) }
}