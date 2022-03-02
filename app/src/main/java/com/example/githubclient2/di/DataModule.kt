package com.example.githubclient2.di

import com.example.githubclient2.data.network.GetUserListNetwork
import com.example.githubclient2.data.network.api.ApiHelper
import com.example.githubclient2.data.network.api.Environment
import com.example.githubclient2.data.network.api.RetrofitClient
import com.example.githubclient2.data.repository.UserListRepositoryImpl
import com.example.githubclient2.domain.repository.UserListRepository
import org.koin.dsl.module
import java.util.Collections.singleton

val dataModule = module {

    factory<GetUserListNetwork> { ApiHelper(RetrofitClient.retrofitServices) }

    factory<UserListRepository> { UserListRepositoryImpl(network = get()) }
}