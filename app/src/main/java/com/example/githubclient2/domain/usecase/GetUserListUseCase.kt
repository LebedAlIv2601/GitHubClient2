package com.example.githubclient2.domain.usecase

import android.util.Log
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository

class GetUserListUseCase(private val userListRepository: UserListRepository) {

    suspend fun execute(id: Int): MutableList<DomainUserModel>{
        Log.e("Loading", "Trying to load data from usecase")
        return userListRepository.get(id)
    }
}