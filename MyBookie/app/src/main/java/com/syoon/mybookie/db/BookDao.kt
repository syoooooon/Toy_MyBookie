package com.syoon.mybookie.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    // favorite 클릭시 아이템 추가
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(books: Favorite)

    // liveData반환 -> 추가되거나 바뀔 때 UI에 업데이트
    // favorite_data의 모든 데이터를 오름차순으로 가져옴
    @Query("SELECT * FROM favorite_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Favorite>>

    // favorite 해제시 아이템 삭제
    @Delete
    suspend fun deleteBook(book: Favorite)

    // favorite 상태 체크
    @Query("UPDATE astrophotoentity SET isFavorite =:isFavorite WHERE id = :id")
    fun isBookMarked(id: String): Boolean

}