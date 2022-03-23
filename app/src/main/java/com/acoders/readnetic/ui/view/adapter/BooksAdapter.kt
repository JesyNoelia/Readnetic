package com.acoders.readnetic.ui.view.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acoders.readnetic.R
import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.databinding.CardBookBinding
import com.acoders.readnetic.ui.view.extensions.inflate
import com.acoders.readnetic.ui.view.extensions.loadUrl

import kotlin.properties.Delegates

class BooksAdapter() : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    internal var bookListener: (Book) -> Unit = {}
    internal var collection: List<Book> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var books: MutableList<Book> = ArrayList()
    lateinit var context: Context

    fun BooksAdapter(books: MutableList<Book>, context: Context) {
        this.books = books
        this.context = context
    }

    override fun getItemCount() = collection.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.card_book))

    override fun onBindViewHolder(holder: BooksAdapter.ViewHolder, position: Int) {
        holder.bind(collection[position], bookListener)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardBookBinding.bind(itemView)
        fun bind(item: Book, bookListener: (Book) -> Unit) {
            binding.tvTitle.text = item.title
            binding.author.text = item.author
            binding.cover.loadUrl(item.bookPicture)
            binding.cardView.setOnClickListener { bookListener(item) }
        }


    }
}
