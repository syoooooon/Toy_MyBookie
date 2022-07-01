package com.syoon.mybookie.viewmodel

import androidx.lifecycle.*
import com.syoon.mybookie.db.Favorite
import com.syoon.mybookie.repository.BookRepository
import com.syoon.mybookie.repository.RoomRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val RoomRepo: RoomRepository): ViewModel() {

    var favList : MutableLiveData<MutableList<Favorite>> = MutableLiveData()

    //favList = RoomRepo.readAllData 로 해야되나..?

    //viewLifecycleOwner 써야될수도...
//    fun fetchFavList(viewLifecycleOwner: LifecycleOwner): MutableLiveData<MutableList<Favorite>> {
//        viewModelScope.launch {
//            favList.observe(viewLifecycleOwner, {
//                favList.value = it
//            })
//        }
//        return favList
//    }

    fun fetchFavList(): MutableLiveData<MutableList<Favorite>> {
        viewModelScope.launch {
            favList.value
        }
        return favList
    }

    // favorite 저장
    fun saveFavorite(favBook: Favorite) {
        viewModelScope.launch {
            RoomRepo.addBook(favBook)
        }
    }

    // favorite 삭제
    fun deleteFavorite(favBook: Favorite) {
        viewModelScope.launch {
            RoomRepo.delete(favBook)
        }
    }
}