package com.example.githubclient2.presentation.details.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.githubclient2.domain.usecase.GetUserInfoUseCase
import com.example.githubclient2.domain.usecase.GetUserListUseCase
import com.example.githubclient2.presentation.main.vm.MainViewModel
import com.example.githubclient2.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DetailsViewModel (private val getUserInfoUseCase: GetUserInfoUseCase) : ViewModel() {

    fun getUserInfo(user: String) = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try{
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.Success(data = getUserInfoUseCase(user = user)))
            Log.e("Loading", "Data loaded")
        } catch(e: Exception){
            emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }

}