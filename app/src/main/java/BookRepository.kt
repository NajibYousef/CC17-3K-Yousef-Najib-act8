package com.example.bookshelf.repository

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BookApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val bookService = retrofit.create(BookApiService::class.java)

    suspend fun getJazzBooks(): List<Book> {
        return try {
            val response = bookService.searchBooks("jazz history")
            response.items
        } catch (e: Exception) {
            emptyList()
        }
    }
}