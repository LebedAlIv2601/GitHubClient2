package com.example.githubclient2.data.network.api

import com.example.githubclient2.data.network.model.DataUserModel
import com.example.githubclient2.domain.model.DomainUserModel

fun DataUserModel.toDomainUserModel(): DomainUserModel{
    return DomainUserModel(
        login = this.login,
        id = this.id,
        avatar = this.avatar)
}