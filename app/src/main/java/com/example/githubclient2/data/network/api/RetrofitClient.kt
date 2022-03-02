package com.example.githubclient2.data.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getClient(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(Environment.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    val retrofitServices: RetrofitServices = getClient().create(RetrofitServices::class.java)
}