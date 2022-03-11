package com.example.githubclient2.data.network.api

import android.util.Log
import com.example.githubclient2.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getClient(): Retrofit{
        Log.e("Loading", "Retrofit Client created")
        return Retrofit.Builder()
                .baseUrl(Environment.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    val retrofitServices: RetrofitServices = getClient().create(RetrofitServices::class.java)
}