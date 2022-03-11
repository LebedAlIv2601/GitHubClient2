package com.example.githubclient2.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.githubclient2.data.network.GetUserListNetwork
import com.example.githubclient2.data.network.api.GitHubPageSource
import com.example.githubclient2.data.network.api.RetrofitServices
import com.example.githubclient2.data.network.model.DataUserModel
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.domain.repository.UserListRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class UserListRepositoryImpl(private val services: RetrofitServices) : UserListRepository, KoinComponent{



    companion object {
        const val DEFAULT_ID_INDEX = 0
        const val DEFAULT_PAGE_SIZE = 30
    }

    override fun getUsers(): LiveData<PagingData<DomainUserModel>> {
//        Log.e("factory", "${factory == null}")
        return Pager(
            config = getDefaultPagingConfig(),
            pagingSourceFactory = {GitHubPageSource(services = services)}).liveData
    }

    private fun getDefaultPagingConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }

}