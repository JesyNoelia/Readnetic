package com.acoders.readnetic.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.acoders.readnetic.R
import com.acoders.readnetic.databinding.FragmentHomeBinding
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.ui.extensions.launchAndCollect
import com.acoders.readnetic.ui.extensions.safeNavigate
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeState: HomeState
    private val adapter = BooksAdapter { homeState.onBookClicked(it) }
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        result.contents?.let {
            println("Scanned: " + result.contents)
            getBookFromQRIsbn(result)
            manageIsbnResult()
        } ?: Toast.makeText(activity, "Cancelled", Toast.LENGTH_SHORT).show()
    }

    private fun manageIsbnResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                println("Book es: $state.book")
                state.book?.let {
                    println("***manageIsbnResult - Book es: $it")
                    navigateToDetail(it)
                } ?: Toast.makeText(activity, "ISBN not valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getBookFromQRIsbn(result: ScanIntentResult) {
        viewModel.loadIsbn(result.contents)
    }

    private fun navigateToDetail(book: Book) {
        findNavController().safeNavigate(
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
