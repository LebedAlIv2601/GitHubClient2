package com.example.githubclient2.data.map

import com.example.githubclient2.data.network.model.DataUserInfoModel
import com.example.githubclient2.data.network.model.DataUserModel
import com.example.githubclient2.domain.model.DomainUserInfoModel
import com.example.githubclient2.domain.model.DomainUserModel

fun DataUserModel.toDomainUserModel(): DomainUserModel{
    return DomainUserModel(
        login = this.login,
        id = this.id,
        avatar = this.avatar)
}

fun DataUserInfoModel.toDomainUserInfoModel(): DomainUserInfoModel{
    return DomainUserInfoModel(
        login = this.login,
        avatar = this.avatar,
        email = this.email,
        company = this.company,
        followers = this.followers,
        following = this.following,
        created = this.created
    )
}