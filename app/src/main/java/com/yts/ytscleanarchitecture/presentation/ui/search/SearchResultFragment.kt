package com.yts.ytscleanarchitecture.presentation.ui.search

import android.os.Bundle
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.yts.ytscleanarchitecture.BR
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.FragmentSearchResultBinding
import com.yts.ytscleanarchitecture.presentation.base.BaseFragment
import com.yts.ytscleanarchitecture.utils.LinearLayoutManagerWrapper
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>() {
    private val searchAdapter: SearchAdapter by inject()
    private val model: SearchViewModel by sharedViewModel()

    companion object {
        fun newInstance(): SearchResultFragment {
            val args = Bundle()
            val fragment = SearchResultFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onLayoutId(): Int {
        return R.layout.fragment_search_result
    }

    override fun setupViewModel(): SparseArray<ViewModel> {
        val setupViewModel = SparseArray<ViewModel>()
        setupViewModel.put(BR.model, model)
        return setupViewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        settingSearchList()
    }

    private fun settingSearchList() {
        list_search.layoutManager =
            LinearLayoutManagerWrapper(context, RecyclerView.VERTICAL, false)
        list_search.adapter = searchAdapter

/*
        *//**
         * 터치시 키보드 가림
         *//*
        list_search.setOnTouchListener { _, _ ->
            activity?.hideKeyboard()
            false
        }*/

    }

    override fun observer() {
        model.listDocument.observe(this, Observer {
            searchAdapter.submitList(it)
            searchAdapter.notifyDataSetChanged()
        })
    }
}