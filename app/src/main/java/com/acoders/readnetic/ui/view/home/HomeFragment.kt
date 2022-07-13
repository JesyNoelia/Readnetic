package com.acoders.readnetic.ui.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.acoders.readnetic.R
import com.acoders.readnetic.databinding.FragmentHomeBinding
import com.acoders.readnetic.ui.view.extensions.launchAndCollect
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


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
            lifecycleScope.launch {
                navigateToDetail(result)
            }
        }
    }

    private fun navigateToDetail(result: ScanIntentResult){
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(result.contents.toInt())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeState = buildMainState()

        val binding = FragmentHomeBinding.bind(view).apply {
            bookListRV.adapter = adapter
        }

        viewModel.onUiReady()

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.books = it.books
            Log.d("fragment", it.books.toString())
            /*binding.error = it.error?.let(homeState::errorToString)*/
            binding.error = it.error
        }

        binding.btnAppDrawer.setOnClickListener {
            barcodeLauncher.launch(configQRScan())
        }

/*binding.searchView.setOnQueryTextListener(object :
    android.widget.SearchView.OnQueryTextListener {
    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: ring): Boolean {
        lifecycleScope.launch {
            binding.bookListRV.apply {
                booksAdapter.BooksAdapter(
                    getBooksByAnyDataUseCase.invoke(query).data as MutableList<Book>,
                    requireContext()
                )
                adapter = booksAdapter
            }
        }
        return true
    }
})*/

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
