package com.yts.ytscleanarchitecture.presentation.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yts.ytscleanarchitecture.R


object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("srcCompat")
    fun srcCompat(view: ImageView, url: String?) {
        if (url != null) {
      //      val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

            Glide.with(view.context)
                .load(url)
                .error(R.drawable.img_error)
               // .transition(withCrossFade(factory))
                .into(view)

        }
    }
}