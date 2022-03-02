package com.example.githubclient2.di

import com.example.githubclient2.data.network.api.ApiHelper
import com.example.githubclient2.data.network.api.Environment
import com.example.githubclient2.data.network.api.RetrofitClient
import org.koin.dsl.module

val dataModule = module {

    factory { ApiHelper(RetrofitClient.retrofitServices) }

}