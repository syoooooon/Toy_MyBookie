package com.syoon.mybookie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDetailData(
    val title : String?,
    val authors : List<String>?  = listOf(""),
    val publishedDate : String,
    val description : String?,
    val pageCount : Int?,
    val previewLink : String?,
    val categories : List<String>? = listOf(""),
    val imageLinks : String?,
) : Parcelable
