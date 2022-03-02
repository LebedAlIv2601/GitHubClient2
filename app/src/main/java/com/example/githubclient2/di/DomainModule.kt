package com.example.githubclient2.di

import com.example.githubclient2.domain.usecase.GetUserListUseCase
import org.koin.dsl.module


val domainModule = module {
    factory<GetUserListUseCase> { GetUserListUseCase(userListRepository = get()) }
}