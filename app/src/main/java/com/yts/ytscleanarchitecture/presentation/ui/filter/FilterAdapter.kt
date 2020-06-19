package com.yts.ytscleanarchitecture.presentation.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.ItemFilterBinding
import com.yts.ytscleanarchitecture.presentation.ui.search.FilterItemClickListener
import com.yts.ytscleanarchitecture.utils.CommonDiffUtil

class FilterAdapter : ListAdapter<String, FilterAdapter.FilterViewHolder>(CommonDiffUtil()) {

    private var filterItemClickListener: FilterItemClickListener? = null


    fun setFilterItemClickListener(filterItemClickListener: FilterItemClickListener) {
        this.filterItemClickListener = filterItemClickListener;
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterViewHolder {
        val item = DataBindingUtil.inflate<ItemFilterBinding>(
            LayoutInflater.from(parent.context), R.layout.item_filter, parent, false
        )
        return FilterViewHolder(item)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class FilterViewHolder(var binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: String) {
            binding.data = data
            binding.textFilter.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                filterItemClickListener?.onFilterItemClick(getItem(adapterPosition))
            }
        }
    }
}