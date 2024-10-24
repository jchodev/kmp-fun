package org.jerry.kmp.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

import org.jerry.kmp.viewmodel.PodcastListViewModel

val viewModelModule = module {
    viewModelOf(::PodcastListViewModel)
}