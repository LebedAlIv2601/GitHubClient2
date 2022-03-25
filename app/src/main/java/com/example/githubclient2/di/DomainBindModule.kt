package com.example.githubclient2.di

import com.example.githubclient2.data.repository.UserInfoRepositoryImpl
import com.example.githubclient2.data.repository.UserListRepositoryImpl
import com.example.githubclient2.domain.repository.UserInfoRepository
import com.example.githubclient2.domain.repository.UserListRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainBindModule{
    @Binds
    fun bindUserListRepositoryImplToUserListRepository(
        userListRepositoryImpl: UserListRepositoryImpl
    ): UserListRepository

    @Binds
    fun bindUserInfoRepositoryImplToUserInfoRepository(
        userInfoRepositoryImpl: UserInfoRepositoryImpl
    ): UserInfoRepository
}