package com.syoon.mybookie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class BookDataBase: RoomDatabase() {

    abstract fun getDao() : BookDao

    // 싱글톤 객체 - 한 번만 생성
    companion object {
        @Volatile // 다른 스레드들이 인스턴스에 변화가 있을 때 바로 확인
        var INSTANCE: BookDataBase? = null

        @Synchronized
        fun getInstance(context: Context): BookDataBase? {
            if (INSTANCE == null) {
                synchronized(BookDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookDataBase::class.java,
                        "book-database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

    //데이터베이스 콜백 함수??
}



