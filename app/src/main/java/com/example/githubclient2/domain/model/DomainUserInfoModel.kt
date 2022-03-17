package com.example.githubclient2.domain.model

data class DomainUserInfoModel(
    val login: String?,
    val avatar: String?,
    val email: String?,
    val company: String?,
    val followers: Int?,
    val following: Int?,
    val created: String?
)
