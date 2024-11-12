package com.example.bookshelf.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.model.Book
import com.example.bookshelf.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val repository = BookRepository()
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _books.value = repository.getJazzBooks()
            _isLoading.value = false
        }
    }
}