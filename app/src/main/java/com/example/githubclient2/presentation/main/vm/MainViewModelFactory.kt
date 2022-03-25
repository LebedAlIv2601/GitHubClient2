package com.example.githubclient2.presentation.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubclient2.domain.usecase.GetUserListUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModelProvider.Factory  {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class.java)
        return MainViewModel(getUserListUseCase) as T
    }
}