package com.syoon.mybookie.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.syoon.mybookie.db.BookDao
import com.syoon.mybookie.db.BookDataBase
import com.syoon.mybookie.db.Favorite

class RoomRepository(private val bookDao: BookDao) {

    //private val bookDao: BookDao = BookDataBase.getInstance(context)!!.getDao()

    // favorite 데이터 가져오기
    var allFavBook: LiveData<List<Favorite>> = bookDao.readAllData()

    suspend fun isFavorite():Boolean {
        allFavBook = bookDao.readAllData()
    }

    suspend fun addBook(favBook: Favorite) {
        bookDao.addBook(favBook)
    }

    suspend fun delete(favBook: Favorite) {
        bookDao.deleteBook(favBook)
    }

//    suspend fun isFavorite(favBook: FavoriteData) = withContext(Dispatchers.IO) {
//
//    }

}