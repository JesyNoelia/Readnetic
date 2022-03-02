package com.acoders.readnetic.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.acoders.readnetic.R
import com.acoders.readnetic.data.model.getBooks
import com.acoders.readnetic.databinding.FragmentHomeBinding
import com.acoders.readnetic.ui.view.adapter.BooksAdapter

class HomeFragment : Fragment(R.layout.fragment_home){
    private val booksAdapter by lazy { BooksAdapter() }

     private lateinit var binding: FragmentHomeBinding
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
            binding.bookListRV.apply {
            booksAdapter.BooksAdapter(getBooks(), requireContext())
            layoutManager = LinearLayoutManager(context)
            adapter = booksAdapter
        }
    }
}