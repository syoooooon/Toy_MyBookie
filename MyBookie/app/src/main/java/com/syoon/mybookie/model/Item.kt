package com.syoon.mybookie.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo,
    //즐겨찾기 디폴트 값
    var isFavBook: Boolean = false
)