package com.acoders.readnetic.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.acoders.readnetic.R
import com.acoders.readnetic.databinding.FragmentDetailBinding
import com.acoders.readnetic.ui.extensions.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val safeArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        saveItemInViewModel()
        initUI()
    }

    private fun initUI() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            state.book?.let { book ->
                binding.book = book
                binding.bookDetailFavorite.setOnClickListener {
                        viewModel.updateFavoriteProperty(!book.favorite).apply { initUI() }
                }
            }
        }
    }

    private fun saveItemInViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.saveItem(safeArgs.book)
        }
    }
}
