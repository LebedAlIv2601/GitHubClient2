package com.example.githubclient2.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.githubclient2.data.network.api.Environment
import com.example.githubclient2.data.paging.GitHubPageSource
import com.example.githubclient2.data.network.api.RetrofitServices
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository

class UserListRepositoryImpl(private val services: RetrofitServices) : UserListRepository{

    override fun getUsers(): LiveData<PagingData<DomainUserModel>> {
        return Pager(
            config = getDefaultPagingConfig(),
            pagingSourceFactory = {GitHubPageSource(services)}).liveData
    }

    private fun getDefaultPagingConfig(): PagingConfig {
        return PagingConfig(pageSize = Environment.DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }

}