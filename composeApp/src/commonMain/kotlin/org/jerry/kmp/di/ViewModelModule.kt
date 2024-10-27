package org.jerry.kmp.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

import org.jerry.kmp.viewmodel.podcastlist.PodcastListViewModel
import org.jerry.kmp.viewmodel.podcastdetail.PodcastDetailViewModel

val viewModelModule = module {
    viewModelOf(::PodcastListViewModel)
    viewModelOf(::PodcastDetailViewModel)
}