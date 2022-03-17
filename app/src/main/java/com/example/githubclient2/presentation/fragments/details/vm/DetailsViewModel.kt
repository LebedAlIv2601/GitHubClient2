package com.example.githubclient2.presentation.fragments.details.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.githubclient2.domain.usecase.GetUserInfoUseCase
import com.example.githubclient2.utils.Resource
import kotlinx.coroutines.Dispatchers

class DetailsViewModel(private val getUserInfoUseCase: GetUserInfoUseCase) : ViewModel() {

    fun getUserInfo(user: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try{
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.success(data = getUserInfoUseCase(user = user)))
            Log.e("Loading", "Data loaded")
        } catch(e: Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }

}