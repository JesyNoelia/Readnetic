package com.acoders.readnetic.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.acoders.readnetic.R
import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.databinding.FragmentHomeBinding
import com.acoders.readnetic.ui.view.adapter.BooksAdapter
import com.acoders.readnetic.usecase.GetBestsellersUseCase
import com.acoders.readnetic.usecase.GetBooksByISBNUseCase
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val booksAdapter by lazy { BooksAdapter() }
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var getBestsellersUseCase: GetBestsellersUseCase
    @Inject
    lateinit var getBooksByISBNUseCase: GetBooksByISBNUseCase

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
        initListeners()
    }

    private fun initListeners() {
        booksAdapter.bookListener = {book ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment2(book))
        }
        binding.btnAppDrawer.setOnClickListener {
            onFBClick()
        }
    }

    private fun initView() {
        lifecycleScope.launch{
            binding.bookListRV.apply {
                booksAdapter.BooksAdapter(getBestsellersUseCase().data as MutableList<Book>, requireContext())
                //layoutManager = LinearLayoutManager(context)
                adapter = booksAdapter
            }
        }
    }

    private fun onFBClick() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.EAN_13)
        options.setPrompt(getString(R.string.message_camera_scanner_isbn))
        options.setCameraId(0) // Use a specific camera of the device
        options.setOrientationLocked(false)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        barcodeLauncher.launch(options)
    }

    // Register the launcher and result handler
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        lifecycleScope.launch {
            if (result.contents != null) {
                val book:Book = getBooksByISBNUseCase(result.contents).data?.get(0) ?: Book()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment2(book))
            }
        }
    }
}