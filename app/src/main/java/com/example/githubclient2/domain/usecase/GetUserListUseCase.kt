package com.example.githubclient2.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository

class GetUserListUseCase(private val userListRepository: UserListRepository) {

    operator fun invoke(): LiveData<PagingData<DomainUserModel>> {
        return userListRepository.getUsers()
    }

//    suspend fun execute(): PagingSource<Int, DomainUserModel>{
//        Log.e("Loading", "Trying to load data from usecase")
//        return userListRepository.get()
//    }
}