package com.example.githubclient2.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.usecase.GetUserListUseCase

class MainViewModel (private val getUserListUseCase: GetUserListUseCase) : ViewModel() {
    val userListData: LiveData<PagingData<DomainUserModel>> get() = getUserListUseCase().cachedIn(viewModelScope)

}