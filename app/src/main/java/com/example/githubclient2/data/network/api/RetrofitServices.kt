package com.example.githubclient2.data.network.api

import com.example.githubclient2.data.network.model.DataUserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {
    @GET("users?per_page=100")
    suspend fun getUserList(@Query ("since") id: Int): MutableList<DataUserModel>
}