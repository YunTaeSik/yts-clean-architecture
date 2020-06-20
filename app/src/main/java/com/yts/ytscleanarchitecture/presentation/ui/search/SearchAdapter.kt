package com.yts.ytscleanarchitecture.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yts.domain.entity.Document
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.SearchItemBinding
import com.yts.ytscleanarchitecture.utils.CommonDiffUtil

class SearchAdapter : ListAdapter<Document, SearchAdapter.SearchViewHolder>(
    CommonDiffUtil()
) {

    inner class SearchViewHolder(var binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Document) {
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val item = DataBindingUtil.inflate<SearchItemBinding>(
            LayoutInflater.from(parent.context), R.layout.item_search, parent, false
        )
        return SearchViewHolder(item)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bind(getItem(position))
        }
    }


}