package com.syoon.mybookie.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
현재 필요한 데이터는
image thumbnail
title
authors
..뿐이지만

나중에 item 클릭 -> detail 정보 뜨게 하려면
상세 정보도 같이 저장해야겠지..?
 */

// 추가 시작
@Entity(tableName = "favorite_data")
data class Favorite(
   // api에서 얻어오는 값
   @PrimaryKey(autoGenerate = true)
   val id: String,
   val title: String,
   val authors: List<String>?,
   val imageLinks: String?,
 // 북마크 확인
   var isFavBook: Boolean? = false
)
