package com.example.githubclient2.data.repository

import com.example.githubclient2.data.network.api.ApiHelper
import com.example.githubclient2.data.map.toDomainUserInfoModel
import com.example.githubclient2.domain.model.DomainUserInfoModel
import com.example.githubclient2.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(private val apiHelper: ApiHelper) : UserInfoRepository {

    override suspend fun getUserInfo(user: String): DomainUserInfoModel {
        return apiHelper.getUserInfoFromNetwork(user = user).toDomainUserInfoModel()
    }
}