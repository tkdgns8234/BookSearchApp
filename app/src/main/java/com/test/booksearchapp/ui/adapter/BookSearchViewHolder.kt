package com.test.booksearchapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.booksearchapp.data.model.Book
import com.test.booksearchapp.databinding.ItemBookPreviewBinding

class BookSearchViewHolder(
    private val binding: ItemBookPreviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        val author = book.authors.toString().removeSurrounding("[", "]")
        val publisher = book.publisher
        val date = if (book.datetime.isNotEmpty()) book.datetime.substring(0, 10) else ""

        with(binding) {
            ivBook.load(book.thumbnail)
            tvAuthor.text = "$author | $publisher"
            tvDatetime.text = date
            tvTitle.text = book.title
        }
//        itemView.apply {
//            // coil 라이브러리 사용해서 image load, 매우 간단하네~_~
//            binding.ivBook.load(book.thumbnail)
//            binding.tvAuthor.text = "$author | $publisher"
//            binding.tvDatetime.text = date
//            binding.tvTitle.text = book.title
//        }
    }

}