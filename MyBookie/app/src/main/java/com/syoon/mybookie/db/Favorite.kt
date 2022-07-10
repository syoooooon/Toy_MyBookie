package com.syoon.mybookie.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

//list와 같은 복잡한 자료형을 넣을 때 자료형을 변환하는 typeconverter를 제공해줘야한다
//authors에 들어가는 자료형 -> List
//https://jinsangjin.tistory.com/56
@Entity(tableName = "favorite_data")
data class Favorite(
    // api에서 얻어오는 값
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo( name = "authors")
    val authors: List<String>?,
    @ColumnInfo(name = "imageLinks")
    val imageLinks: String?,
    var isFavBook: Boolean? = false
)

//type converter
class TypeConverterFavorite {
    private val gson: Gson = Gson()

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}
