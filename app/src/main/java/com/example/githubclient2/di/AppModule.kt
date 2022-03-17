package com.example.githubclient2.di

import com.example.githubclient2.presentation.fragments.details.vm.DetailsViewModel
import com.example.githubclient2.presentation.fragments.main.vm.MainViewModel
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