package com.example.githubclient2.presentation.fragments.main.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.githubclient2.domain.usecase.GetUserListUseCase
import com.example.githubclient2.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val getUserListUseCase: GetUserListUseCase) : ViewModel() {

    fun getUserList() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try{
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.success(data = getUserListUseCase.execute(0)))
            Log.e("Loading", "Data loaded")
        } catch(e: Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }


}