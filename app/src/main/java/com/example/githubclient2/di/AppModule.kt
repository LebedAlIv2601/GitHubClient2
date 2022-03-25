package com.example.githubclient2.di

import com.example.githubclient2.presentation.details.vm.DetailsViewModel
import com.example.githubclient2.presentation.main.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel>{
        MainViewModel(getUserListUseCase = get())
    }

    viewModel <DetailsViewModel>{
        DetailsViewModel(getUserInfoUseCase = get())
    }

}