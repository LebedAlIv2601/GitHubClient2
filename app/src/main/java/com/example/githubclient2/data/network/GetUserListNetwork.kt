package com.example.githubclient2.data.network

import androidx.paging.PagingSource
import com.example.githubclient2.data.network.model.DataUserModel

interface GetUserListNetwork {

    suspend fun get(id: Int): PagingSource<Int, DataUserModel>

}