package com.syoon.mybookie.repository

import com.syoon.mybookie.model.VolumeInfo
import com.syoon.mybookie.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository {

    private val api1 = RetrofitClient.bookAPI

    suspend fun searchBooks(searchKeyWord: String): List<VolumeInfo> = withContext(Dispatchers.IO) {
        api1.searchBook(searchKeyWord).items
    }
}