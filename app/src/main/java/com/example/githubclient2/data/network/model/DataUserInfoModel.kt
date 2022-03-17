package com.example.githubclient2.data.network.model

import com.google.gson.annotations.SerializedName

data class DataUserInfoModel(
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatar: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("followers") val followers: Int?,
    @SerializedName("following") val following: Int?,
    @SerializedName("created_at") val created: String?
)
