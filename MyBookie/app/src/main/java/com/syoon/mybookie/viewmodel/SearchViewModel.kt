package com.syoon.mybookie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syoon.mybookie.model.Item
import com.syoon.mybookie.repository.BookRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val BookRepo = BookRepository()
    var booksList : MutableLiveData<List<Item>> = MutableLiveData()

    fun fetchBooksList(searchKeyWord: String? = null): LiveData<List<Item>> {
        viewModelScope.launch {
            try {
                if (searchKeyWord.isNullOrEmpty()) {
                    //booksList.postValue(repo.bestbooks()) - 검색 안했을 때 베스트셀러나 다른 화면 띄우기
                } else {
                    booksList.postValue(BookRepo.searchBooks(searchKeyWord))
                }
            } catch (e: Throwable) {
                Log.e("Books search", "Downloaded Books failed ${e.localizedMessage}")
            }
        }
        return booksList
    }
}