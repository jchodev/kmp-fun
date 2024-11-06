package org.jerry.kmp

import android.app.Application
import org.jerry.kmp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class KmpApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }

        initKoin {
            androidLogger()
            androidContext(this@KmpApp)
        }
    }
}