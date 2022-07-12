package com.syoon.mybookie.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.syoon.mybookie.ui.adapter.SearchAdapter
import com.syoon.mybookie.databinding.FragmentSearchBinding
import com.syoon.mybookie.viewmodel.SearchViewModel


// fragment viewBinding - https://gift123.tistory.com/58
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    val searchVM: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    var adapter = SearchAdapter()

    // 메인 액티비티에서 접근하기 위한 인스턴스
    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }

    // LiveData, recyclerview Adapter .. 셋팅
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // grid layout
        binding.rvSearch.layoutManager = GridLayoutManager(context, 2)

        loadBooks()

        // 검색어 입력
        binding.searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // 입력 글자
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(tag, "Query text : $query")
                loadBooks(query?.trim()) //여백 없애기
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(tag, "Query text : $newText")
                return false
            }
        })

        //favorite 클릭 이벤트로 룸에 저장(FavoriteVM에서 저장 및 isFavorited 상태 변경)
    // -> adapter에서 상태에 따라 뷰 띄우기 (일단 favorite db랑 연결하고 삭제 추가되는거 실시간으로 반영... 가능한가요:)

    }

    @SuppressLint("LongLogTag")
    private fun loadBooks(query: String? = null) {
        binding.rvSearch.adapter = adapter
        searchVM.fetchBooksList(query).observe(viewLifecycleOwner, {
            // 입력값 없을 때
            if (query.isNullOrEmpty()) {
                //binding.rvSearch.adapter = BookAdapter()
            } else {
                adapter.setBookList(it)
            }
            Log.d("Google books main Response", it.toString())
        })
    }
}