package com.example.githubclient2.domain.usecase

import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository

class GetUserListUseCase(private val userListRepository: UserListRepository) {

    private suspend fun execute(id: Int): MutableList<DomainUserModel>{
        return userListRepository.get(id)
    }
}