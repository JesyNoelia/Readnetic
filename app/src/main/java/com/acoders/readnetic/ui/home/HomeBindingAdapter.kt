package com.acoders.readnetic.ui.home

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acoders.readnetic.domain.Book

@BindingAdapter("items")
fun RecyclerView.setItems(books: List<Book>?) {
    if (books != null) {
        Log.d("***bindingAdapter",books.toString())
        (adapter as? BooksAdapter)?.submitList(books)
    }
}