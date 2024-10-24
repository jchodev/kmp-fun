package org.jerry.kmp.di

import org.jerry.kmp.data.repository.PodcastRepository
import org.jerry.kmp.data.repository.impl.PodcastRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PodcastRepository> { PodcastRepositoryImpl(get()) }
}