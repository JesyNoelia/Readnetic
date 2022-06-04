package com.acoders.readnetic.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.acoders.readnetic.R
import com.acoders.readnetic.databinding.FragmentDetailBinding
import com.acoders.readnetic.ui.view.detail.DetailViewModel
import com.acoders.readnetic.ui.view.extensions.loadUrl

class DetailFragment: Fragment(R.layout.fragment_detail) {

   /* private val viewModel: DetailViewModel by viewModels {
        //DetatailViewModelFactory(arguments?.getParcelable<>())
    }*/

    private lateinit var binding: FragmentDetailBinding

    private val safeArgs: DetailFragmentArgs by navArgs()
    //private val viewModel: DetailViewModel by viewModels{}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        binding.name.text = safeArgs.book.authors.toString()
        binding.date.text = safeArgs.book.date
        binding.collapsingToolbar.title = safeArgs.book.title
        binding.descriptionTv.text = safeArgs.book.description
        safeArgs.book.bookPicture?.let { binding.bookImageCT.loadUrl(it) }
    }
}