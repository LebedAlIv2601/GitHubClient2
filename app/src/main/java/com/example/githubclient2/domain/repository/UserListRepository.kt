package com.example.githubclient2.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.githubclient2.domain.model.DomainUserModel

interface UserListRepository {

    fun getUsers(): LiveData<PagingData<DomainUserModel>>
}