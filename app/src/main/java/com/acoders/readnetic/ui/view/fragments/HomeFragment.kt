package com.acoders.readnetic.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.acoders.readnetic.R
import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.model.getBooks
import com.acoders.readnetic.databinding.FragmentHomeBinding
import com.acoders.readnetic.ui.view.adapter.BooksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val booksAdapter by lazy { BooksAdapter() }
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var repository: BookRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        lifecycleScope.launch{
            binding.bookListRV.apply {
                booksAdapter.BooksAdapter(repository.getAllBooksByAnyData("harry potter").data as MutableList<Book>, requireContext())
                layoutManager = LinearLayoutManager(context)
                adapter = booksAdapter
            }
        }

    }
}