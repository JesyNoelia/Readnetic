package com.acoders.readnetic.ui.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.acoders.readnetic.R
import com.acoders.readnetic.databinding.FragmentDetailBinding
import com.acoders.readnetic.ui.view.extensions.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val safeArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        binding.bookDetailFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
        viewModel.loadIsbn(safeArgs.book)

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.book != null) {
                binding.book = state.book
            }
        }
    }
}