package com.akinguldere.etsturcase.utils.extensions

import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.databinding.BindingAdapter
import com.akinguldere.etsturcase.R
import com.akinguldere.etsturcase.utils.Constants
import com.bumptech.glide.Glide

@BindingAdapter("urlImage")
fun bindUrlImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load("${Constants.IMAGE_BASE_URL}${url}").let { request ->
            request.into(view)
        }
    } else {
        // if image has a problem
        view.setImageResource(R.drawable.etstur)
    }
}

@BindingAdapter("onBackPressed")
fun bindOnBackPressed(view: View, onBackPress: Boolean) {
    val context = view.context
    if (onBackPress && context is OnBackPressedDispatcherOwner) {
        view.setOnClickListener {
            context.onBackPressedDispatcher.onBackPressed()
        }
    }
}
