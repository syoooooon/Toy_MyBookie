package com.syoon.mybookie.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syoon.mybookie.R
import com.syoon.mybookie.databinding.FragmentSearchBinding
import com.syoon.mybookie.databinding.SearchItemBinding
import com.syoon.mybookie.model.Item


// dataBinding 사용
class SearchAdapter : RecyclerView.Adapter<SearchAdapter.BookViewHolder>() {

    private var bookList: List<Item> = listOf()


    //LiveData 세팅 메서드 - 보여줄 데이터 목록 업데이트
    @SuppressLint("NotifyDataSetChanged")
    fun setBookList(data: List<Item>) {
        this.bookList = data
        notifyDataSetChanged() //데이터가 변경되면 옵저버에게 통보
    }


    //뷰홀더에 값 지정
    inner class BookViewHolder(private val binding: FragmentSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context //추가-intent parameter

        fun bindData(book: Item) {
            //레이아웃에서 사용할 데이터 설정
            binding.search = book.volumeInfo //volumeInfo
            binding.executePendingBindings()

//            //item click event
//            itemView.setOnClickListener {
//                var intent = Intent(context, BookWebViewActivity::class.java).apply {
//                    putExtra("info_link", bookList[0].volumeInfo.infoLink)
//                }.run { context.startActivity(this) }
//            }
        }
    }

    // Layout에서 이미지 URL값이 변동되었을 때 호출되는 Binding Adapter
    companion object {
        @BindingAdapter("loadImage")
        fun loadImage(thumbImage: ImageView, url: String?) {
            url?.let{
                Glide.with(thumbImage.context)
                    .load(url)
                    .error(R.drawable.ic_launcher_background)
                    .into(thumbImage)
            }
        }
    }


    //생성할 뷰홀더 xml 지정 - xml 레이아웃에 매핑되는 Binding class 생성
    //해당 class로 layout 생성해서 ViewHolder에 넘겨줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(layoutInflater)
        return BookViewHolder(binding)
    }

    //뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindData(bookList[position])
    }

    //뷰홀더 갯수 리턴
    override fun getItemCount(): Int {
        return bookList.size
    }
}