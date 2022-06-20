package com.syoon.mybookie.network

import com.syoon.mybookie.model.BookData
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi{
    @GET("books/v1/volumes") //"books/v1/volumes?q=kotlin"
    suspend fun searchBooks(@Query("q") searchKey: String) : BookData
}