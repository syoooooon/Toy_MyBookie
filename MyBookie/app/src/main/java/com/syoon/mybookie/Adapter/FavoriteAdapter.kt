package com.syoon.mybookie.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syoon.mybookie.R
import com.syoon.mybookie.databinding.FavoriteItemBinding
import com.syoon.mybookie.databinding.SearchItemBinding
import com.syoon.mybookie.db.Favorite
import com.syoon.mybookie.model.Item

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {

    private var favBookList: List<Favorite> = listOf()

    //LiveData 세팅 메서드 - 보여줄 데이터 목록 업데이트
    @SuppressLint("NotifyDataSetChanged")
    fun setBookList(data: List<Favorite>) {
        this.favBookList = data
        notifyDataSetChanged() //데이터가 변경되면 옵저버에게 통보
    }

    inner class FavViewHolder(private val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(favBook: Favorite) {
            //레이아웃에서 사용할 데이터 설정
            binding.favorite = favBook
            binding.executePendingBindings()

            //heart 클릭 이벤트
//            binding.ivLike.setOnClickListener {
//                if(b.isFavorite) {
//                    binding.ivLike.setImageResource(R.drawable.ic_favorite_filled)
//                    book.isFavBook = !book.isFavBook
//
//                    val favInfo = Favorite(
//                        book.id,
//                        book.volumeInfo.title,
//                        book.volumeInfo.authors,
//                        book.volumeInfo.publishedDate,
//                        book.volumeInfo.description,
//                        book.volumeInfo.imageLinks?.smallThumbnail.toString(),
//                        book.isFavBook
//
//                                favoriteVM.saveFavorite(favInfo)
//
//                } else {
//                    binding.ivLike.setImageResource(R.drawable.ic_favorite_border)
//                    book.isFavBook = !book.isFavBook
//                    favoriteVM.deleteFavorite()
//                }
//            }

        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbImage: ImageView, url: String?) {
            url?.let {
                Glide.with(thumbImage.context)
                    .load(url)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_book_24) //url이 null값일 때 보여주는 이미지
                    .into(thumbImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavViewHolder, position: Int) {
        holder.bindData(favBookList[position])
    }

    override fun getItemCount(): Int {
        return favBookList.size
    }

}

//        private val thumbnail = itemView.findViewById<ImageView>(R.id.item_book_marked_list_iv)
//        private val name = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_name)
//        private val rate = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_rate)
//        private val isBookMarked =
//            itemView.findViewById<CheckBox>(R.id.item_book_marked_list_btn_book_mark)
//        private val msg = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_msg)
//
//        fun bind(bookMark: BookMark) {
//            // thumbnail
//            Glide.with(itemView).load(bookMark.thumbnail).into(thumbnail)
//            // name
//            name.text = bookMark.name
//            // rate
//            rate.text = bookMark.rate.toString()
//            // bookMark
//            GlobalScope.launch {
//                isBookMarked.isChecked =
//                    (itemView.context.applicationContext as BookMarkApplication).repository.isBookMarked(
//                        bookMark.id
//                    )
//            }
//            // msg
//            msg.text =
//                SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(bookMark.timeStamp)) + "에 찜 하셨어요!"
//        }
//
//    }




