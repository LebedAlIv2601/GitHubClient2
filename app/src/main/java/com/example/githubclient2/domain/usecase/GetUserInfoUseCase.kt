package com.example.githubclient2.domain.usecase

import com.example.githubclient2.domain.model.DomainUserInfoModel
import com.example.githubclient2.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val userInfoRepository: UserInfoRepository) {

    suspend operator fun invoke(user: String): DomainUserInfoModel {
        return userInfoRepository.getUserInfo(user)
    }
}