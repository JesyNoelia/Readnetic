package com.acoders.readnetic.ui.extensions

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.DiffUtil
import com.acoders.readnetic.ReadneticApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T : Any> basicDiffUtil(
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame(oldItem, newItem)
}

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

val Context.app: ReadneticApp
    get() = applicationContext as ReadneticApp

fun NavController.safeNavigate(direction: NavDirections) {
    println("Navcontroller Click happened")
    currentDestination?.getAction(direction.actionId)?.run {
        println("Navcontroller Click Propagated")
        navigate(direction)
    }
}