package com.example.githubclient2.domain.repository

import com.example.githubclient2.domain.model.DomainUserInfoModel

interface UserInfoRepository {

    suspend fun getUserInfo(user: String): DomainUserInfoModel
}