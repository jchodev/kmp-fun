package org.jerry.kmp.di


import org.jerry.kmp.data.database.AppDatabase
import org.jerry.kmp.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> { getDatabaseBuilder(get()) }
}