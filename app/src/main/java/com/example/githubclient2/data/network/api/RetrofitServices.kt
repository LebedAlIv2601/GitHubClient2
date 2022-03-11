package com.example.githubclient2.data.network.api

import com.example.githubclient2.data.network.model.DataUserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {

    @Headers("Authorization: token ${Token.TOKEN}")
    @GET("users")
    suspend fun getUserList(@Query ("since") id: Int,
                            @Query ("per_page") perPage: Int):
            MutableList<DataUserModel>
}