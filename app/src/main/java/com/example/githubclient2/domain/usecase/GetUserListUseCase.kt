package com.example.githubclient2.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val userListRepository: UserListRepository) {

    operator fun invoke(): LiveData<PagingData<DomainUserModel>> {
        return userListRepository.getUsers()
    }
}