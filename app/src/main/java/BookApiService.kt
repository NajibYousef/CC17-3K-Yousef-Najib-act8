package com.example.bookshelf.network

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BookApiService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): BookSearchResponse

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Book

    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }
}