package com.example.githubclient2.presentation.details.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubclient2.domain.usecase.GetUserInfoUseCase
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == DetailsViewModel::class.java)
        return DetailsViewModel(getUserInfoUseCase) as T
    }
}