package com.example.bookshelf.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bookshelf.R
import com.example.bookshelf.databinding.ItemBookBinding
import com.example.bookshelf.model.Book

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var books = listOf<Book>()

    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.bookTitle.text = book.volumeInfo.title
            book.volumeInfo.imageLinks?.thumbnail?.let { url ->
                val httpsUrl = url.replace("http:", "https:")
                binding.bookImage.load(httpsUrl) {
                    crossfade(true)
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size

    fun submitList(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}