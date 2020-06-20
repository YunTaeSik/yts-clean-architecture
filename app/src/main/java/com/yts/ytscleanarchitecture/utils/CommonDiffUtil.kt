package com.yts.ytscleanarchitecture.utils

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.Gson

class CommonDiffUtil<B> : DiffUtil.ItemCallback<B>() {
    override fun areItemsTheSame(oldItem: B, newItem: B): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: B, newItem: B): Boolean {
        return (Gson().toJson(oldItem)) == (Gson().toJson(newItem))
    }


}