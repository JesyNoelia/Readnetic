package com.acoders.readnetic.ui.view.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.acoders.readnetic.R
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import kotlinx.coroutines.CoroutineScope

fun Fragment.buildMainState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),

) = HomeState(context, scope, navController)

class HomeState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController,


) {
    fun onBookClicked(book: com.acoders.readnetic.domain.Book) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(book.id)
        navController.navigate(action)
    }



    fun errorToString(error: com.acoders.readnetic.domain.Error) = when (error) {
        com.acoders.readnetic.domain.Error.Connectivity -> context.getString(R.string.connectivity_error)
        is com.acoders.readnetic.domain.Error.Server -> context.getString(R.string.server_error) + error.code
        is com.acoders.readnetic.domain.Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }

}