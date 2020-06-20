package com.yts.ytscleanarchitecture.presentation.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yts.domain.entity.Document
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.SearchItemBinding
import com.yts.ytscleanarchitecture.presentation.ui.image.ImageDetailActivity
import com.yts.ytscleanarchitecture.utils.CommonDiffUtil
import com.yts.ytscleanarchitecture.utils.Const

class SearchAdapter : ListAdapter<Document, SearchAdapter.SearchViewHolder>(
    CommonDiffUtil<Document>()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val item: SearchItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_search, parent, false
        )
        return SearchViewHolder(item)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bind(getItem(position))
        }
    }

    inner class SearchViewHolder(var binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(data: Document) {
            binding.data = data
            binding.image.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context: Context = binding.image.context

            var pair: Pair<View, String> = Pair.create(binding.image, Const.TRANS_VIEW_IMAGE)
            val optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context as Activity,
                    pair
                )
            binding.data
            var imageDetail :Intent= Intent(context, ImageDetailActivity::class.java)
            imageDetail.putExtra(Const.INTENT_IMAGE_URL, getItem(adapterPosition)?.image_url)

            context.startActivity(
                imageDetail, optionsCompat.toBundle()
            )
        }
    }


}