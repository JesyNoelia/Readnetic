package com.acoders.readnetic.ui.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.acoders.readnetic.ui.extensions.loadUrl

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null) loadUrl(url)
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.GONE
}