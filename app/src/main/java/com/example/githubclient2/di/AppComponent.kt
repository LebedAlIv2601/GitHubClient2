package com.example.githubclient2.di

import com.example.githubclient2.presentation.details.fragment.DetailsFragment
import com.example.githubclient2.presentation.main.fragment.MainFragment
import dagger.Component



@Component(modules = [DataModule::class, DomainBindModule::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(detailsFragment: DetailsFragment)
}


