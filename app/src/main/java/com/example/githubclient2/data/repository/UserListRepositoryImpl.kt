package com.example.githubclient2.data.repository

import com.example.githubclient2.data.network.GetUserListNetwork
import com.example.githubclient2.data.network.model.DataUserModel
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository

class UserListRepositoryImpl(private val network: GetUserListNetwork) : UserListRepository {


    override suspend fun get(id: Int): MutableList<DomainUserModel> {
        return mapUser(network.get(id))
    }

    private fun mapUser(users: MutableList<DataUserModel>): MutableList<DomainUserModel>{
        val newUsers: MutableList<DomainUserModel> = mutableListOf()
        users.forEach {
            newUsers.add(DomainUserModel(it.login, it.id, it.avatar))
        }
        return newUsers
    }
}