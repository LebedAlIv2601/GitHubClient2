package com.example.githubclient2.di

import com.example.githubclient2.presentation.fragments.main.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{
        MainViewModel(getUserListUseCase = get())
    }

}