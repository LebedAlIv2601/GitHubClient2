package com.example.githubclient2.data.network.api

import com.example.githubclient2.data.network.model.DataUserInfoModel
import javax.inject.Inject

class ApiHelper @Inject constructor(private val services: RetrofitServices) {

    suspend fun getUserInfoFromNetwork(user: String): DataUserInfoModel{
        return services.getUserInfo(user)
    }

}