package com.acoders.readnetic.ui.view.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.ui.view.adapter.BooksAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(books: List<Book>?) {
    if (books != null) {
        (adapter as? BooksAdapter)?.submitList(books)
    }
}