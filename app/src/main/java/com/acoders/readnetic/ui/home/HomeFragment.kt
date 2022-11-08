package com.acoders.readnetic.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.acoders.readnetic.R
import com.acoders.readnetic.databinding.FragmentHomeBinding
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.ui.extensions.launchAndCollect
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeState: HomeState
    private val adapter = BooksAdapter { homeState.onBookClicked(it) }
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(activity, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
                getBookFromQRIsbn(result)
                state.book?.let { navigateToDetail(it) }
            }
        }
    }

    private fun getBookFromQRIsbn(result: ScanIntentResult) {
        viewModel.loadIsbn(result.contents)
    }

    private fun navigateToDetail(book: Book) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(book)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeState = buildMainState()

        val binding = FragmentHomeBinding.bind(view).apply {
            bookListRV.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.books = it.books
            println("List loaded in fragment: ${it.books}")
            binding.error = it.error?.let(homeState::errorToString)
        }

        binding.btnQr.setOnClickListener {
            barcodeLauncher.launch(configQRScan())
        }
    }

    private fun configQRScan(): ScanOptions {
        return ScanOptions()
            .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            .setPrompt(getString(R.string.message_camera_scanner_isbn))
            .setCameraId(0) // Use a specific camera of the device
            .setOrientationLocked(false)

            .setBeepEnabled(false)
            .setBarcodeImageEnabled(true)
    }
}
