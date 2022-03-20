package com.example.githubclient2.di


import com.example.githubclient2.data.network.api.*
import com.example.githubclient2.data.repository.UserInfoRepositoryImpl
import com.example.githubclient2.data.repository.UserListRepositoryImpl
import com.example.githubclient2.domain.repository.UserInfoRepository
import com.example.githubclient2.domain.repository.UserListRepository
import org.koin.dsl.module


val dataModule = module {

    factory<UserListRepository> {
        UserListRepositoryImpl(RetrofitClient.retrofitServices)
    }

    factory <UserInfoRepository> { UserInfoRepositoryImpl(apiHelper = get())  }

    factory <ApiHelper> { ApiHelper(services = RetrofitClient.retrofitServices) }

}