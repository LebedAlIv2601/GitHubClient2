package com.example.githubclient2.data.network

import com.example.githubclient2.data.network.model.DataUserModel

interface GetUserListNetwork {

    suspend fun get(id: Int): MutableList<DataUserModel>

}