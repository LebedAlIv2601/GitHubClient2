package com.example.githubclient2.data.network.model

import com.google.gson.annotations.SerializedName

data class DataUserModel(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatar: String?
)
