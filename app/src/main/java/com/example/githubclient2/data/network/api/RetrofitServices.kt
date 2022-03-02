package com.example.githubclient2.data.network.api

import com.example.githubclient2.data.network.model.DataUserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("users?since={id}&per_page=50")
    suspend fun getUserList(@Path ("id") id: Int): Call<MutableList<DataUserModel>>
}