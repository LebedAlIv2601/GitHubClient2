package com.example.githubclient2.data.network.api

import com.example.githubclient2.data.network.model.DataUserInfoModel

class ApiHelper(private val services: RetrofitServices) {

    suspend fun getUserInfoFromNetwork(user: String): DataUserInfoModel{
        return services.getUserInfo(user)
    }

}