package com.acoders.readnetic.ui.view.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.acoders.readnetic.R
import com.acoders.readnetic.data.database.BooksDatabase
import com.acoders.readnetic.data.typeconverters.Converters
import com.acoders.readnetic.databinding.CardBookBinding
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.ui.view.extensions.basicDiffUtil
import com.acoders.readnetic.ui.view.extensions.inflate


class BooksAdapter(
    private val listener: (Book) -> Unit
) : ListAdapter<Book, BooksAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }){


    var books: MutableList<Book> = ArrayList()
    lateinit var context: Context

    private var roomDatabase: BooksDatabase? = null



    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view= parent.inflate(R.layout.card_book, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        roomDatabase = Room.databaseBuilder(
            holder.itemView.context,
            BooksDatabase::class.java, "BooksDB"
        )
            .addTypeConverter(Converters())
            .build()


        val book = getItem(position)
        holder.bind(book)
        holder.itemView.setOnClickListener { listener(book) }


       /* holder.bind(books[position], bookListener)
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
        }*/
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardBookBinding.bind(itemView)
        fun bind(book: Book){
            binding.book = book
        }
       /* fun bind(item: Book, bookListener: (Book) -> Unit) {
            binding.tvTitle.text = item.title
            binding.author.text = item.authors.toString()
            binding.cover.loadUrl(item.bookPicture ?: "")
            item.bookPicture?.let { binding.cover.loadUrl(it) }
            binding.cardView.setOnClickListener {
                //Toast.makeText(context,"clicked, ${item}", Toast.LENGTH_SHORT).show()
                bookListener(item) }
        }*/


    }

    /*suspend fun insertBookToDB(bookInfo: BookEntity) {
        val bookDao = roomDatabase?.booksDao()
        bookDao?.insert(bookInfo)
}*/
}