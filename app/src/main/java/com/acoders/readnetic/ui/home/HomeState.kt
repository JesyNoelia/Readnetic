package com.acoders.readnetic.ui.home

import android.content.Context

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.acoders.readnetic.R
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error

fun Fragment.buildMainState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = HomeState(context, navController)

class HomeState(
    private val context: Context,
    private val navController: NavController,
) {
    fun onBookClicked(book: Book) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(book)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}
