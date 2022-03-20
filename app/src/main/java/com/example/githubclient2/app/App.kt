package com.example.githubclient2.app

import android.app.Application
import com.example.githubclient2.BuildConfig
import com.example.githubclient2.di.appModule
import com.example.githubclient2.di.dataModule
import com.example.githubclient2.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}