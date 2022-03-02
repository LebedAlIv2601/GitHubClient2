package com.example.githubclient2.domain.repository

import com.example.githubclient2.domain.model.DomainUserModel

interface UserListRepository {

    suspend fun get(id: Int): MutableList<DomainUserModel>
}