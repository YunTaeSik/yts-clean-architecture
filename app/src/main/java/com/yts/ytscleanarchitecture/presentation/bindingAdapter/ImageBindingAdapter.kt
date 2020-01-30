package com.yts.ytscleanarchitecture.presentation.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.yts.ytscleanarchitecture.R

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("srcCompat")
    fun srcCompat(view: ImageView, url: String) {
        val circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 40f
        circularProgressDrawable.start()

        Glide.with(view.context).load(url).thumbnail(0.1f)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.img_error)
            .into(view)
    }
}