package org.jerry.kmp.di

import org.jerry.kmp.network.EpisodeApi
import org.jerry.kmp.network.PodcastApi
import org.jerry.kmp.network.impl.EpisodeApiImpl
import org.jerry.kmp.network.impl.PodcastApiImpl
import org.koin.dsl.module

val apiModule = module {
    single<PodcastApi> { PodcastApiImpl(get()) }
    single<EpisodeApi> { EpisodeApiImpl(get()) }
}