package com.syoon.mybookie.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.syoon.mybookie.R
import com.syoon.mybookie.databinding.FragmentDetailsBinding


//https://hanyeop.tistory.com/231
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private lateinit var previewUrl: String

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //binding으로 바꾸기
    //@SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bookImgV.load(args.booksInfoKey.imageLinks?.thumbnail)//?.replace("zoom=1","zoom=0"))

        Glide.with(view)
            .load(args.booksInfo.imageLinks?.toString())
            .placeholder(R.drawable.ic_book_24)
            .error(R.drawable.ic_launcher_background)
            .into(binding.ivbookD)

        binding.tvTitle1D.text = args.booksInfo.title
        binding.tvTitle2D.text = args.booksInfo.title

        if (args.booksInfo.pageCount == null) {
            binding.tvPages.isVisible = false
            binding.tvPagesD.isVisible = false
        } else {
            binding.tvPagesD.text = args.booksInfo.pageCount.toString()
        }
        binding.tvPublishDateD.text = args.booksInfo.publishedDate

        if (args.booksInfo.categories == null) {
            binding.tvCategories.isVisible = false
            binding.tvCategoriesD.isVisible = false

        } else {
            var categoryPrint = ""
            args.booksInfo.categories?.let {
                for (i in it) {
                    if (i == it.last())
                        categoryPrint += "$i ."
                    else
                        categoryPrint += "$i , "
                }
            }
            binding.tvCategoriesD.text = categoryPrint

        }

        if (args.booksInfo.authors == null) {
            binding.tvAuthor.isVisible = false
            binding.tvAuthorD.isVisible = false

        } else {
            var authorsPrint = ""
            args.booksInfo.authors?.let {
                for (i in it) {
                    if (i == it.last())
                        authorsPrint += "$i ."
                    else
                        authorsPrint += "$i , "
                }
            }
            binding.tvAuthorD.text = authorsPrint
        }

        if (args.booksInfo.description == null) {
            binding.tvDescriptionD.text = getString(R.string.no_description)
        } else {
            binding.tvDescriptionD.text = args.booksInfo.description.toString()
        }


        previewUrl = args.booksInfo.previewLink.toString()
        binding.btnPreview.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, previewUrl.toUri())
            startActivity(intent)
        }
    }
}