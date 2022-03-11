package com.example.githubclient2.data.network.api

interface PageSourceFactory {

    fun create(): GitHubPageSource
}