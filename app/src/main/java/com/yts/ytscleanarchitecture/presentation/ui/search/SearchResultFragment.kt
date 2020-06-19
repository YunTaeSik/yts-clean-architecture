package com.yts.ytscleanarchitecture.presentation.ui.search

import android.os.Bundle
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.yts.ytscleanarchitecture.BR
import com.yts.ytscleanarchitecture.R
import com.yts.ytscleanarchitecture.databinding.FragmentSearchResultBinding
import com.yts.ytscleanarchitecture.presentation.base.BaseFragment
import com.yts.ytscleanarchitecture.presentation.ui.filter.FilterAdapter
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(), FilterItemClickListener {
    private val searchAdapter: SearchAdapter by inject()
    private val filterAdapter: FilterAdapter by inject()

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
        settingFilterList()
        settingSearchList()
    }

    private fun settingFilterList() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list_filter.layoutManager = layoutManager
        filterAdapter.setFilterItemClickListener(this)
        list_filter.adapter = filterAdapter

    }

    private fun settingSearchList() {
        val layoutManager = GridLayoutManager(context, 3)
        list_search.layoutManager = layoutManager
        list_search.adapter = searchAdapter
    }


    /*
        */
    /**
     * 터치시 키보드 가림
     *//*
        list_search.setOnTouchListener { _, _ ->
            activity?.hideKeyboard()
            false
        }*/

    override fun observer() {
        model.filterHashSet.observe(this, Observer { filterHashSet ->
            val filterList: List<String> = ArrayList<String>(filterHashSet)
            filterAdapter.submitList(filterList)
        })

        model.documentList.observe(this, Observer {
            searchAdapter.submitList(it)
        })
    }

    override fun onFilterItemClick(filter: String) {
        model.setFilter(filter)
    }

}