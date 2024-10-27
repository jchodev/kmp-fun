package org.jerry.kmp.di

import org.jerry.kmp.data.repository.EpisodeRepository
import org.jerry.kmp.data.repository.PodcastRepository
import org.jerry.kmp.data.repository.impl.EpisodeRepositoryImpl
import org.jerry.kmp.data.repository.impl.PodcastRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PodcastRepository> { PodcastRepositoryImpl(get()) }
    single<EpisodeRepository> { EpisodeRepositoryImpl(get()) }
}