package com.syoon.mybookie.repository

import android.content.Context
import com.syoon.mybookie.db.BookDao
import com.syoon.mybookie.db.BookDataBase
import com.syoon.mybookie.db.Favorite

class RoomRepository(context: Context) {

    private val bookDao: BookDao = BookDataBase.getInstance(context)!!.getDao()

    // favorite 데이터 가져오기
    //val readAllData: LiveData<List<FavoriteData>> = bookDao.readAllData()


//    suspend fun isFavorite():Boolean {
//        //bookDao.readAllData()
//    }

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