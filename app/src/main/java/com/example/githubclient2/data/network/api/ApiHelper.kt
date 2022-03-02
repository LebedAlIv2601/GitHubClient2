package com.example.githubclient2.data.network.api

import com.example.githubclient2.data.network.GetUserListNetwork
import com.example.githubclient2.data.network.model.DataUserModel

class ApiHelper (val services: RetrofitServices) : GetUserListNetwork {

    override suspend fun get(id: Int): MutableList<DataUserModel>{
        return services.getUserList(id)
    }

}