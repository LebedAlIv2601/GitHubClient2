package com.example.githubclient2.di

import androidx.paging.PagingSource
import com.example.githubclient2.data.network.GetUserListNetwork
import com.example.githubclient2.data.network.api.*
import com.example.githubclient2.data.repository.UserListRepositoryImpl
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository
import org.koin.dsl.module
import java.util.Collections.singleton

val dataModule = module {


    factory<UserListRepository> { UserListRepositoryImpl(RetrofitClient.retrofitServices) }
}