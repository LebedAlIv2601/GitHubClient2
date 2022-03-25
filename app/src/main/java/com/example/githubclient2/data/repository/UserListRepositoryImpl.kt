package com.example.githubclient2.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.githubclient2.data.network.api.Environment
import com.example.githubclient2.data.paging.GitHubPageSource
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository
import javax.inject.Inject
import javax.inject.Provider

class UserListRepositoryImpl @Inject constructor(
    private val gitHubPageSourceFactory: Provider<GitHubPageSource>) : UserListRepository{

    override fun getUsers(): LiveData<PagingData<DomainUserModel>> {
        return Pager(
            config = getDefaultPagingConfig(),
            pagingSourceFactory = {gitHubPageSourceFactory.get()}).liveData
    }

    private fun getDefaultPagingConfig(): PagingConfig {
        return PagingConfig(pageSize = Environment.DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }

}