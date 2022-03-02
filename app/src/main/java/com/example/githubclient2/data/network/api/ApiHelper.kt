package com.example.githubclient2.data.network.api

class ApiHelper (val services: RetrofitServices) {

    suspend fun getUsers(id: Int){
        services.getUserList(id)
    }

}