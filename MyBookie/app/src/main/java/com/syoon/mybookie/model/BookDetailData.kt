package com.syoon.mybookie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDetailData(
    val id: String,
    val bookSmallThumbnail: String?,
    val title: String,
    val publisher: String?,
    val bookDescription: String?,
    val previewLink: String?,
    val bookThumbnail: String?,
    var isFavourite: Boolean? = false
) : Parcelable
