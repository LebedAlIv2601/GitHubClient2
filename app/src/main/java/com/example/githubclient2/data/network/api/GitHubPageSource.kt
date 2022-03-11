package com.example.githubclient2.data.network.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.githubclient2.domain.model.DomainUserModel
import retrofit2.HttpException
import java.lang.Exception

class GitHubPageSource(private val services: RetrofitServices) : PagingSource<Int, DomainUserModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainUserModel> {

        return try {
            val id: Int = params.key ?: 0
            val response = services.getUserList(id, params.loadSize)

            val users = response.map { it.toDomainUserModel()}
            val nextId = if (users.isEmpty()) null else users[users.size - 1].id
            val prevId = null

            Log.e("Items", "New Items Loaded from id $id. Items loaded: ${users.size}")
            LoadResult.Page(users, prevId, nextId)
        } catch (e: HttpException){
            Log.e("ErrorHTTP", "${e.message()}")
            return LoadResult.Error(e)
        } catch (e: Exception){
            Log.e("Error", "${e.message}")
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DomainUserModel>): Int = 0

//    interface Factory {
//        fun create(): GitHubPageSource
//    }
}

