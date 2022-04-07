package com.acoders.readnetic.ui.view.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.acoders.readnetic.R
import com.acoders.readnetic.data.database.BooksDatabase
import com.acoders.readnetic.data.database.entity.BookEntity
import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.typeconverters.Converters
import com.acoders.readnetic.databinding.CardBookBinding
import com.acoders.readnetic.ui.view.extensions.inflate
import com.acoders.readnetic.ui.view.extensions.loadUrl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kotlin.properties.Delegates

class BooksAdapter() : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    internal var bookListener: (Book) -> Unit = {}
    internal var collection: List<Book> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var books: MutableList<Book> = ArrayList()
    lateinit var context: Context

    private var roomDatabase: BooksDatabase? = null

    fun BooksAdapter(books: MutableList<Book>, context: Context) {
        this.books = books
        this.context = context
    }

    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.card_book))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        roomDatabase = Room.databaseBuilder(
            holder.itemView.context,
            BooksDatabase::class.java, "BooksDB"
        )
            .addTypeConverter(Converters())
            .build()

        holder.bind(books[position], bookListener)
        holder.itemView.setOnClickListener {
            var bookInfo= books[position].title?.let { it1 ->
                books[position].description?.let { it2 ->
                    BookEntity(0, books[position].authors?.toMutableList(),
                        it1, it2
                    )
                }
            }
            if (bookInfo != null) {
                GlobalScope.launch {
                    insertBookToDB(bookInfo)
                }
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardBookBinding.bind(itemView)
        fun bind(item: Book, bookListener: (Book) -> Unit) {
            binding.tvTitle.text = item.title
            binding.author.text = item.authors.toString()
            binding.cover.loadUrl(item.bookPicture ?: "")
            item.bookPicture?.let { binding.cover.loadUrl(it) }
            binding.cardView.setOnClickListener {
                //Toast.makeText(context,"clicked, ${item}", Toast.LENGTH_SHORT).show()
                bookListener(item) }
        }


    }

    suspend fun insertBookToDB(bookInfo: BookEntity) {
        val bookDao = roomDatabase?.booksDao()
        bookDao?.insert(bookInfo)
}
}