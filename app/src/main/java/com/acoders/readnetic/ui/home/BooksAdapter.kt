package com.acoders.readnetic.ui.home

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acoders.readnetic.R
import com.acoders.readnetic.databinding.CardBookBinding
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.ui.extensions.basicDiffUtil
import com.acoders.readnetic.ui.extensions.inflate


class BooksAdapter(private val listener: (Book) -> Unit) :
    ListAdapter<Book, BooksAdapter.ViewHolder>(basicDiffUtil { old, new -> old.isbn == new.isbn }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.card_book, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
        Log.d("***click",holder.itemView.toString())
        holder.itemView.setOnClickListener {
            listener(book) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardBookBinding.bind(itemView)

        fun bind(book: Book) {
            binding.book = book
        }
    }
}
