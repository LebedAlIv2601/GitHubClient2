package com.example.githubclient2.di

import com.example.githubclient2.data.network.api.Environment
import com.example.githubclient2.data.network.api.RetrofitServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class DataModule{


    @Provides
    fun provideServices(): RetrofitServices {
        val retrofit  = Retrofit.Builder()
            .baseUrl(Environment.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitServices::class.java)
    }
}
