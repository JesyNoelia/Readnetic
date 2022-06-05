package com.acoders.readnetic.ui.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.acoders.readnetic.R
import com.acoders.readnetic.data.database.BooksDatabase
import com.acoders.readnetic.data.typeconverters.Converters
import com.acoders.readnetic.databinding.FragmentHomeBinding
import com.acoders.readnetic.ui.view.adapter.BooksAdapter
import com.acoders.readnetic.ui.view.extensions.launchAndCollect
import com.acoders.readnetic.usecase.GetBestsellersUseCase
import com.acoders.readnetic.usecase.GetBooksByAnyDataUseCase
import com.acoders.readnetic.usecase.GetBooksByISBNUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeState: HomeState

    private val adapter = BooksAdapter{homeState.onBookClicked(it)}

    @Inject
    lateinit var getBestsellersUseCase: GetBestsellersUseCase

    @Inject
    lateinit var getBooksByISBNUseCase: GetBooksByISBNUseCase

    @Inject
    lateinit var getBooksByAnyDataUseCase: GetBooksByAnyDataUseCase

    private var roomDatabase: BooksDatabase? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeState = buildMainState()

        val binding = FragmentHomeBinding.bind(view).apply{
            bookListRV.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.books = it.books
            binding.error = it.error?.let(homeState::errorToString)
        }

        viewModel.onUiReady()



        initView()
        //initListeners()
    }


    private fun initView() {
        roomDatabase = Room
            .databaseBuilder(requireContext(), BooksDatabase::class.java, "BooksDB")
            .addTypeConverter(Converters())
            .build()




        /*lifecycleScope.launch {
            binding.bookListRV.apply {
                booksAdapter.BooksAdapter(
                    getBestsellersUseCase().data as MutableList<Book>,
                    requireContext()
                )
                //layoutManager = LinearLayoutManager(context)
                adapter = booksAdapter
            }
        }*/
    }



   /* private fun initListeners() {
        booksAdapter.bookListener = { book ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment2(
                    book
                )
            )
        }
        binding.btnAppDrawer.setOnClickListener {
            onFBClick()
        }
        binding.searchView.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
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
        })
    }*/


    /*private fun onFBClick() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.EAN_13)
        options.setPrompt(getString(R.string.message_camera_scanner_isbn))
        options.setCameraId(0) // Use a specific camera of the device
        options.setOrientationLocked(false)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        barcodeLauncher.launch(options)
    }*/

    // Register the launcher and result handler
    /*private val barcodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            lifecycleScope.launch {
                if (result.contents != null) {
                    val book: Book = getBooksByISBNUseCase(result.contents).data?.get(0) ?: Book()
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                            book
                        )
                    )
                }
            }
        }*/
}