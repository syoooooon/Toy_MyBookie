package com.syoon.mybookie.repository

import com.syoon.mybookie.model.Item
import com.syoon.mybookie.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository {

    private val api = RetrofitClient.bookAPI

    // 검색 안했을 때 베스트셀러나 다른 화면 띄우기
//    suspend fun bestList(): List<VolumeInfo> = withContext(Dispatchers.IO) {
//        api1.bestBooks().items
//    }

    // 검색 결과 데이터
    suspend fun searchBooks(searchKeyWord: String): List<Item> = withContext(Dispatchers.IO) {
        api.searchBooks(searchKeyWord).items
    }
}