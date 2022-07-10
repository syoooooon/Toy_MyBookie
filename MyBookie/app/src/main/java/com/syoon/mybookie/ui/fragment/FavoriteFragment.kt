package com.syoon.mybookie.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.syoon.mybookie.ui.adapter.FavoriteAdapter
import com.syoon.mybookie.databinding.FragmentFavoriteBinding
import com.syoon.mybookie.repository.RoomRepository
import com.syoon.mybookie.viewmodel.FavoriteViewModel
import com.syoon.mybookie.viewmodel.FavoriteViewModelFactory


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteVM by lazy {
        ViewModelProvider(this, FavoriteViewModelFactory(RoomRepository(requireContext()))).get(FavoriteViewModel::class.java)
    }

    var favAdapter = FavoriteAdapter()

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // grid layout
        binding.rvFavorite.layoutManager = GridLayoutManager(context, 2)

        loadBooks()

    }

    @SuppressLint("LongLogTag")
    private fun loadBooks() {
        binding.rvFavorite.adapter = favAdapter
        favoriteVM.fetchFavList().observe(viewLifecycleOwner, {
            favAdapter.setBookList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}








//    private lateinit var booksRVFav: RecyclerView
//    private lateinit var favAdapter: FavoriteAdapter
//    private lateinit var favIcon: ImageView
//    private lateinit var favText: TextView





//    private fun loadBooks() {
//
//        val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
//
//        vm.fetchFavList(viewLifecycleOwner).observe(viewLifecycleOwner, {
//            if (it.isNullOrEmpty()) {
//                favIcon.setVisibility(View.VISIBLE)
//                favText.setVisibility(View.VISIBLE)
//                booksRVFav.setVisibility(View.GONE)
//            }else{
//                favIcon.setVisibility(View.GONE)
//                favText.setVisibility(View.GONE)
//            }
//            favAdapter = FavoriteAdapter(it, vm, scaleUp, favIcon,favText)
//            booksRVFav.adapter = favAdapter
//
//        })
//    }
//
//    // Recyclerview
//    val wishlistAdapter = WishlistAdapter(this)
//    binding.bookWishListRecyclerView.adapter = wishlistAdapter
//    binding.bookWishListRecyclerView.layoutManager = LinearLayoutManager(this)
//
//    // BookDataViewModel
//    mBookDataViewModel = ViewModelProvider(this).get(BookDataViewModel::class.java)
//    mBookDataViewModel.readAllData.observe(this, Observer { book ->
//        wishlistAdapter.setData(book)
//    })
//}
//binding.wishlistImageView.setOnClickListener {
//
//    if (book.isFavourite == true) {
//
//        binding.wishlistImageView.setImageResource(R.drawable.ic_empty_star)
//        mBookDataViewModel.deleteBook(book)
//        book.isFavourite = false
//
//        Toast.makeText(applicationContext, "Book removed from wishlist", Toast.LENGTH_LONG)
//            .show()
//    } else {
//
//        binding.wishlistImageView.setImageResource(R.drawable.ic_filled_star)
//        mBookDataViewModel.addBook(book)
//        book.isFavourite = true
//
//        Toast.makeText(applicationContext, "Book added to wishlist", Toast.LENGTH_LONG)
//            .show()
//    }
//}
//}

