package com.example.githubclient2.presentation.fragments.main.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.usecase.GetUserListUseCase

class MainViewModel(private val getUserListUseCase: GetUserListUseCase) : ViewModel() {

    private val _userListData = MutableLiveData<PagingData<DomainUserModel>>()
    val userListData: LiveData<PagingData<DomainUserModel>> get() = getUserListUseCase().cachedIn(viewModelScope)

//    init{
//        _userListData.value = getUserList().value
//    }
//
//    fun getUserList(): LiveData<PagingData<DomainUserModel>>{
//        Log.e("Loading", "Trying to load data from vm")
//        return getUserListUseCase().cachedIn(viewModelScope)
//    }

}