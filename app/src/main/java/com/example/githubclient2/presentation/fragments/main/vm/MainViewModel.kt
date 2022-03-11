package com.example.githubclient2.presentation.fragments.main.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.usecase.GetUserListUseCase
import com.example.githubclient2.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val getUserListUseCase: GetUserListUseCase) : ViewModel() {

    fun getUserList(): LiveData<PagingData<DomainUserModel>>{
        Log.e("Loading", "Trying to load data from vm")
        return getUserListUseCase().cachedIn(viewModelScope)
    }

//    fun getUserList() = liveData(Dispatchers.IO){
//        emit(Resource.loading(data = null))
//        try{
//            Log.e("Loading", "Trying to load data from vm")
//            emit(Resource.success(data = getUserListUseCase().cachedIn(viewModelScope)))
//            Log.e("Loading", "Data loaded")
//        } catch(e: Exception){
//            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!!!"))
//        }
//    }


}