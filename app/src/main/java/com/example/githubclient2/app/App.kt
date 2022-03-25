package com.example.githubclient2.app

import android.app.Application
import android.content.Context
import com.example.githubclient2.BuildConfig
import com.example.githubclient2.di.*

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}

val Context.appComponent: AppComponent
    get() = when(this){
        is App -> appComponent
        else -> applicationContext.appComponent
    }